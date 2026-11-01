export function outlineToMarkdown(title, outline) {
  const lines = [];
  lines.push(`# ${title}`);
  lines.push('');
  lines.push(`## 검색 의도`);
  lines.push(outline.search_intent || '');
  lines.push('');
  lines.push(`## 도입부`);
  lines.push(outline.intro || '');
  lines.push('');
  lines.push(`## 본문`);
  for (const sec of outline.sections || []) {
    lines.push(`### ${sec.heading}`);
    for (const p of sec.points || []) lines.push(`- **핵심**: ${p}`);
    if (sec.example) lines.push(`- *예시*: ${sec.example}`);
    lines.push('');
  }
  lines.push(`## 마무리`);
  lines.push(outline.outro || '');
  lines.push('');
  if (outline.hashtags?.length) {
    lines.push(`## 해시태그`);
    lines.push(outline.hashtags.join(' '));
    lines.push('');
  }
  if (outline.image_ideas?.length) {
    lines.push(`## 이미지 아이디어`);
    for (const i of outline.image_ideas) lines.push(`- ${i}`);
    lines.push('');
  }
  if (outline.notes?.length) {
    lines.push(`## 유의점`);
    for (const n of outline.notes) lines.push(`- ${n}`);
  }
  return lines.join('\n');
}

export function escapeHtml(str) {
  return String(str ?? '')
    .replaceAll('&', '&amp;')
    .replaceAll('<', '&lt;')
    .replaceAll('>', '&gt;')
    .replaceAll('"', '&quot;')
    .replaceAll("'", '&#39;');
}
