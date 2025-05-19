import { createElement } from "../../libs/dom.js";
import { Option } from "../models/Option.js";

/**
 * @param {HTMLElement} element
 * @param {(value: string) => Promise<any>} onSelect
 */
function dropDown(element, onSelect = async () => {}) {
  /**
   * @param {Option[]} options 
   */
  const setOptions = (options) => {
    const optElements = options.map((val)=>{
      const op = createElement('option', {class: 'app-filter-dropdown-option', value: val.value, innerHTML: val.label});
      return op;
    });
    element.innerHTML = '';
    element.append(...optElements);
  }

  const disable = () => {
    element.disabled = true;
  }

  const enable = () => {
    element.disabled = false;
  }

  /**
   * @param {string} value 
   */
  const _handleSelect = async (value) => {
    try {
      await onSelect(value);
    }
    catch (error) {
      console.error(error);
    }
  }

  element.onchange = async (e)=>{
    await _handleSelect(e.target.value);
  }

  return { setOptions, enable, disable}
}

export default dropDown;
