#!/usr/bin/env python3
"""Build a PBO report DOCX with a fixed, minimal style.

Usage:
    python build_docx.py --body body.md --out Laporan.docx
    python build_docx.py --body body.md --out Laporan.docx --template template.docx
"""

import argparse
import io
import os
import re

from docx import Document
from docx.enum.table import WD_TABLE_ALIGNMENT
from docx.enum.text import WD_ALIGN_PARAGRAPH
from docx.oxml import OxmlElement
from docx.oxml.ns import qn
from docx.shared import Inches, Mm, Pt, RGBColor

FONT = "Times New Roman"
CODE_FONT = "Courier New"
BODY = 11
BLACK = RGBColor(0x00, 0x00, 0x00)


def cell_margins(cell, top=0.04, start=0.06, bottom=0.04, end=0.06):
    tcPr = cell._tc.get_or_add_tcPr()
    mar = OxmlElement("w:tcMar")
    for side, val in (("top", top), ("start", start), ("bottom", bottom), ("end", end)):
        el = OxmlElement("w:" + side)
        el.set(qn("w:w"), str(int(val * 1440)))
        el.set(qn("w:type"), "dxa")
        mar.append(el)
    tcPr.append(mar)


def table_borders(table):
    tblPr = table._tbl.tblPr
    borders = OxmlElement("w:tblBorders")
    for edge in ("top", "left", "bottom", "right", "insideH", "insideV"):
        el = OxmlElement("w:" + edge)
        el.set(qn("w:val"), "single")
        el.set(qn("w:sz"), "8")
        el.set(qn("w:space"), "0")
        el.set(qn("w:color"), "000000")
        borders.append(el)
    tblPr.append(borders)


def no_split_rows(table):
    for row in table.rows:
        trPr = row._tr.get_or_add_trPr()
        trPr.append(OxmlElement("w:cantSplit"))


def keep_table_together(table):
    for row in table.rows[:-1]:
        for cell in row.cells:
            for p in cell.paragraphs:
                p.paragraph_format.keep_with_next = True


def add_runs(p, text, size=BODY, bold=False):
    for part in re.split(r"(\*\*.+?\*\*|`[^`]+`)", text):
        if not part:
            continue
        if part.startswith("**") and part.endswith("**") and len(part) > 4:
            r = p.add_run(part[2:-2])
            r.bold = True
            r.font.name = FONT
            r.font.size = Pt(size)
            r.font.color.rgb = BLACK
        elif part.startswith("`") and part.endswith("`") and len(part) > 2:
            r = p.add_run(part[1:-1])
            r.bold = bold
            r.font.name = CODE_FONT
            r.font.size = Pt(size - 1)
            r.font.color.rgb = BLACK
        else:
            r = p.add_run(part)
            r.bold = bold
            r.font.name = FONT
            r.font.size = Pt(size)
            r.font.color.rgb = BLACK


def add_heading(doc, text, level):
    p = doc.add_paragraph()
    add_runs(p, text, size={1: 14, 2: 13, 3: 12}[level], bold=True)
    pf = p.paragraph_format
    pf.space_before = Pt({1: 16, 2: 12, 3: 10}[level])
    pf.space_after = Pt({1: 6, 2: 4, 3: 3}[level])
    pf.keep_with_next = True
    return p


def add_body(doc, text):
    p = doc.add_paragraph()
    add_runs(p, text)
    p.alignment = WD_ALIGN_PARAGRAPH.JUSTIFY
    p.paragraph_format.line_spacing = 1.5
    p.paragraph_format.space_after = Pt(6)
    return p


def add_caption(doc, text):
    p = doc.add_paragraph()
    r = p.add_run(text)
    r.italic = True
    r.font.name = FONT
    r.font.size = Pt(9)
    r.font.color.rgb = BLACK
    p.paragraph_format.space_before = Pt(4)
    p.paragraph_format.space_after = Pt(4)
    return p


def add_spacer(doc, pts=8):
    p = doc.add_paragraph()
    p.paragraph_format.space_before = Pt(0)
    p.paragraph_format.space_after = Pt(0)
    p.paragraph_format.line_spacing = Pt(pts)
    p.paragraph_format.keep_with_next = True
    r = p.add_run("")
    r.font.size = Pt(1)
    return p


def add_code(doc, code):
    lines = code.split("\n")
    for i, ln in enumerate(lines):
        p = doc.add_paragraph()
        pf = p.paragraph_format
        pf.left_indent = Inches(0.2)
        pf.space_before = Pt(0)
        pf.space_after = Pt(0)
        pf.line_spacing = 1.0
        pf.keep_with_next = True
        r = p.add_run(ln if ln else " ")
        r.font.name = CODE_FONT
        r.font.size = Pt(9)
        r.font.color.rgb = BLACK
    add_spacer(doc, 8)


def add_bullet(doc, text):
    p = doc.add_paragraph()
    p.alignment = WD_ALIGN_PARAGRAPH.JUSTIFY
    p.paragraph_format.left_indent = Inches(0.3)
    p.paragraph_format.first_line_indent = Inches(-0.2)
    p.paragraph_format.space_after = Pt(3)
    r = p.add_run("\u2022  ")
    r.font.name = FONT
    r.font.size = Pt(BODY)
    r.font.color.rgb = BLACK
    add_runs(p, text)
    return p


def add_table(doc, rows):
    if not rows:
        return
    cols = max(len(r) for r in rows)
    t = doc.add_table(rows=0, cols=cols)
    t.alignment = WD_TABLE_ALIGNMENT.CENTER
    t.autofit = True
    table_borders(t)

    tblPr = t._tbl.tblPr
    width = OxmlElement("w:tblW")
    width.set(qn("w:type"), "pct")
    width.set(qn("w:w"), "5000")
    tblPr.append(width)

    for ri, row in enumerate(rows):
        cells = t.add_row().cells
        for ci in range(cols):
            txt = row[ci] if ci < len(row) else ""
            cell = cells[ci]
            cell_margins(cell)
            p = cell.paragraphs[0]
            p.paragraph_format.space_before = Pt(0)
            p.paragraph_format.space_after = Pt(0)
            p.paragraph_format.line_spacing = 1.0
            add_runs(p, txt, size=BODY, bold=(ri == 0))

    no_split_rows(t)
    keep_table_together(t)
    add_spacer(doc, 8)


def clear_header_footer(doc):
    for sec in doc.sections:
        for part in (sec.header, sec.footer):
            for p in part.paragraphs:
                for child in list(p._p):
                    if child.tag != qn("w:pPr"):
                        p._p.remove(child)


def prepare_template(doc):
    clear_header_footer(doc)
    last = -1
    for i, p in enumerate(doc.paragraphs):
        if p.text.strip():
            last = i
    if last >= 0:
        for p in list(doc.paragraphs)[last + 1:]:
            p._element.getparent().remove(p._element)


def make_plain_document():
    doc = Document()
    sec = doc.sections[0]
    sec.page_width = Mm(210)
    sec.page_height = Mm(297)
    sec.left_margin = Inches(1)
    sec.right_margin = Inches(1)
    sec.top_margin = Inches(1)
    sec.bottom_margin = Inches(1)

    st = doc.styles["Normal"]
    st.font.name = FONT
    st.font.size = Pt(BODY)
    st.font.color.rgb = BLACK
    return doc


def is_block(line):
    s = line.strip()
    return (
        not s
        or s.startswith("```")
        or s.startswith("~ ")
        or s.startswith("- ")
        or re.match(r"^#{1,3}\s", s)
        or s.startswith("|")
    )


def build(body_path, out_path, template_path=None):
    if template_path:
        doc = Document(template_path)
        prepare_template(doc)
    else:
        doc = make_plain_document()

    md = io.open(body_path, encoding="utf-8").read().replace("\r\n", "\n")
    lines = md.split("\n")

    i, n = 0, len(lines)
    while i < n:
        s = lines[i].strip()

        if not s:
            i += 1
            continue

        if s == "\\pagebreak":
            doc.add_page_break()
            i += 1
            continue

        if s.startswith("@center"):
            rest = s[len("@center"):].strip()
            if re.fullmatch(r"\d+", rest):
                size = int(rest)
                text = " "
            else:
                m = re.match(r"^(\d+)\s+(.*)$", rest)
                if m:
                    size = int(m.group(1))
                    text = m.group(2)
                else:
                    size = BODY
                    text = rest
            p = doc.add_paragraph()
            p.alignment = WD_ALIGN_PARAGRAPH.CENTER
            r = p.add_run(text)
            r.bold = True
            r.font.name = FONT
            r.font.size = Pt(size)
            r.font.color.rgb = BLACK
            p.paragraph_format.space_before = Pt(0)
            p.paragraph_format.space_after = Pt(6)
            i += 1
            continue

        if s.startswith("```"):
            i += 1
            buf = []
            while i < n and not lines[i].strip().startswith("```"):
                buf.append(lines[i])
                i += 1
            i += 1
            add_code(doc, "\n".join(buf))
            continue

        if s.startswith("~ "):
            add_caption(doc, s[2:].strip())
            i += 1
            continue

        m = re.match(r"^(#{1,3})\s+(.*)$", s)
        if m:
            add_heading(doc, m.group(2).strip(), len(m.group(1)))
            i += 1
            continue

        if s.startswith("|"):
            rows = []
            while i < n and lines[i].strip().startswith("|"):
                cells = [c.strip() for c in lines[i].strip().strip("|").split("|")]
                if not all(re.fullmatch(r":?-{2,}:?", c) for c in cells if c):
                    rows.append(cells)
                i += 1
            add_table(doc, rows)
            continue

        if s.startswith("- "):
            add_bullet(doc, s[2:].strip())
            i += 1
            continue

        buf = [s]
        i += 1
        while i < n and not is_block(lines[i]):
            buf.append(lines[i].strip())
            i += 1
        add_body(doc, " ".join(buf))

    doc.save(out_path)
    return out_path


def verify(path):
    d = Document(path)
    issues = []

    for sec in d.sections:
        if any(p.text.strip() for p in sec.header.paragraphs):
            issues.append("header berisi teks")
        if any(p.text.strip() for p in sec.footer.paragraphs):
            issues.append("footer berisi teks")

    for shd in d.element.body.iter(qn("w:shd")):
        fill = (shd.get(qn("w:fill")) or "").upper()
        if fill and fill not in ("FFFFFF", "AUTO"):
            issues.append("shading " + fill)

    for t in d.tables:
        for row in t.rows:
            for cell in row.cells:
                for p in cell.paragraphs:
                    for r in p.runs:
                        if r.font.size and abs(r.font.size.pt - BODY) > 0.1:
                            issues.append("font tabel " + str(r.font.size.pt))

    if issues:
        return "PERIKSA: " + "; ".join(sorted(set(issues)))
    return "OK: header/footer kosong, tanpa shading, font tabel " + str(BODY) + " pt"


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--body", required=True)
    parser.add_argument("--out", required=True)
    parser.add_argument("--template")
    args = parser.parse_args()

    out = build(args.body, args.out, args.template)
    print("selesai:", out)
    print("ukuran :", round(os.path.getsize(out) / 1024, 1), "KB")
    print(verify(out))


if __name__ == "__main__":
    main()
