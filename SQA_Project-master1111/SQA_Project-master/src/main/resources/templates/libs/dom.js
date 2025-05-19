/**
 * Lấy ra một phần tử HTML bằng selector.
 * @param {string} selector 
 */
export function $(selector){
  return document.querySelector(selector);
}

/**
 * Lấy ra nodelist chứa các phần tử HTML bằng selector.
 * @param {string} selector 
 */
export function $$(selector){
  return document.querySelectorAll(selector);
}

/**
 * Tạo ra một phần tử HTML.
 * @param {keyof HTMLElementTagNameMap} tagName 
 * @param {{[name: string]: any}} attributes id, class, src, ...
 * @param {CSSStyleDeclaration} styles 
 */
export function createElement(tagName, attributes = {}, styles = undefined){
  const e = document.createElement(tagName);
  for(let k of Object.keys(attributes)){
    if (k==='class') e.className += attributes[k];
    else if (k==='id') e.id = attributes[k];
    else e[k] = attributes[k];
  }
  if (styles){
    Object.assign(e.style, styles);
  }
  return e;
}

export default {$, $$, createElement}