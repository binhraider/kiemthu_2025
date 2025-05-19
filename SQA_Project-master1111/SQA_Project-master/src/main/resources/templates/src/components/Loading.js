import {createElement} from "../../libs/dom.js";

const loadingImgAttrs = {class: 'loading-img'};
const loadingCtnAttrs = {class: 'loading-ctn'};
/**
 * @param {HTMLElement} container Container chứa loading.
 * @returns 
 */
function loading (container) {
  /**
   * @type {HTMLElement}
   */
  const _container = container ?? document.body;
  let _element;
  const _init = () => {
    const loadingImg = createElement('div', loadingImgAttrs);
    const loadingCtn = createElement('div', loadingCtnAttrs, {position: container ? 'absolute' : 'fixed'});
    loadingCtn.append(loadingImg);
    _element = loadingCtn;
  }

  _init();

  const show = () => {
    _container.append(_element);
  }

  const hide = () => {
    _element.remove();
  }

  return {show, hide}
}

export default loading;