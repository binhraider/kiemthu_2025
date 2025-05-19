import { createElement } from "../../libs/dom.js";

export const VALUES = {
  ASC: { value: 'asc', icon: 'fa fa-caret-up'},
  DESC: {value: 'desc', icon: 'fa fa-caret-down'}
}

/**
 * @param {HTMLElement} element
 * @param {(value: string) => void} onChange
 */
function sortParam(element, onChange = ()=>{}, currentValue = VALUES.ASC) {
  let _currentValue = {...currentValue};
  let _currentIcon = createElement('i', {class: _currentValue.icon});
  
  const _initElement = () => {
    element.classList.add('subject-param');
    element.append(_currentIcon);
    _enable();
  }

  const _toggleCurrentValue = () => {
    if(_currentValue.value === VALUES.DESC.value) {
      _currentValue = {...VALUES.ASC};
    }
    else {
      _currentValue = {...VALUES.DESC};
    }
  }
  
  const _handleChange = () => {
    _disable();
    _toggleCurrentValue();
    const {icon, value} = _currentValue;
    _currentIcon.className = icon;
    try {
      onChange(value);
    }
    catch (error) {
      console.error(error);
    }
    finally {
      _enable();
    }
  }
  
  const _disable = () => {
    element.onclick = null;
    element.setAttribute('data-disabled', true);
  }
  
  const _enable = () => {
    element.removeAttribute('data-disabled');
    element.onclick = () => {
      _handleChange();
    }
  }

  _initElement();
}

export default sortParam;