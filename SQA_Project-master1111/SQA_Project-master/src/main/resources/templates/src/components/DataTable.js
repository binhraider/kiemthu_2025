import { Subject } from "../models/Subject.js";
import { createElement } from "../../libs/dom.js"
/**
 * @param {HTMLTableElement} tableElement một thẻ table có chứa tbody để render các môn học.
 * @param {(isSelected: boolean, subject: Subject) => Promise<any>} onSelectSubject hàm xử lý sự kiện click vào ô input subject.
*/
function dataTable(tableElement, onSelectSubject = () => {}) {
  const td = (content) => createElement('td', {innerHTML: content});
  
  const _container = tableElement.tBodies[0];
  
  /**
   * @param {Subject} subject
   * @returns {HTMLTableRowElement}
  */
 const _renderRow = (subject) => {
   const tr = createElement('tr');
   let {id, name, group} = subject;
    const tds = [td(id), td(name), td(group)];
    tr.append(_renderCheckboxCtn(subject), ...tds);
    return tr;
  }
  
  /**
   * @param {Subject} subject
   * @returns {HTMLTableCellElement}
   */
  const _renderCheckboxCtn = (subject) => {
    const checkboxCtn = createElement('td');
    const checkbox = createElement('input', {type: 'checkbox'});
    checkbox.onchange = async (e) => {
      try {
        checkbox.disabled = true;
        await onSelectSubject(e.target.checked, subject);
      }
      catch (error) {
        console.error(error);
      }
      finally {
        checkbox.disabled = false;
      }
    }
    checkboxCtn.append(checkbox);
    return checkboxCtn;
  }

  /**
   * @param {Subject[]} data 
   */
  const setData = (data) => {
    const rows = data.map((subject)=>_renderRow(subject));
    _container.innerHTML = '';
    _container.append(...rows);
  }

  return { setData };
}

export default dataTable;