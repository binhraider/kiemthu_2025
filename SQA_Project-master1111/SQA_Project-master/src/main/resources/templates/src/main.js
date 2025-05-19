import { $ } from "../libs/dom.js";
import loading from "./components/Loading.js";
import dropDown from "./components/Dropdown.js";
import sortParam from "./components/SortParam.js";
import dataTable from "./components/DataTable.js";
import subjectService from "./services/subject.service.js";

const screenLoading = loading();
screenLoading.show();

window.onload = () => {
  // html elements
  const hDataTableCtn = $('#data-table-ctn');
  const hDataTable = $('#data-table');
  const hSubjectFilter = $('#filter-dropdown');

  // main data table
  const cDataTableLoading = loading(hDataTableCtn);

  // subject events
  const onSelectSubjectFilter = async (val) => {
    subjectService.filterSubjects({testParam: val});
  }
  const onSelectSubject = async (isSelected, subject) => {
    if (isSelected) {
      subjectService.filterSubjects({});
    }
    else {
      subjectService.filterSubjects({});
    }
  }
  const onParamChange = (paramName) => {
    return (val) => {
      subjectService.filterSubjects({[paramName]: val});
    }
  }

  // components
  const cDataTable = dataTable(hDataTable, onSelectSubject);
  const cSubjectFilter = dropDown(hSubjectFilter, onSelectSubjectFilter);
  const cSortParams = (()=>{
    // id
    sortParam($('#subject-param-id'), onParamChange('id'));
    // name
    sortParam($('#subject-param-name'), onParamChange('name'));
  })();
 
  // services event listeners
  const serviceEventListeners = {
    onFetching: () => {
      cDataTableLoading.show();
      cSubjectFilter.disable();
    },
    onSuccess: () => {
      cDataTableLoading.hide();
      cSubjectFilter.enable();
    },
    onError: () => {
      cDataTableLoading.hide();
      cSubjectFilter.enable();
    },
  }
  subjectService.setServiceEventListeners(serviceEventListeners);
  
  // subscribe to services
  subjectService.subscribe(res => {cDataTable.setData(res ?? [])});
  
  // done initialization
  screenLoading.hide();

  // call service
  // subjectService.getSubjectFilters((data)=> cSubjectFilter.setOptions(data));
  subjectService.filterSubjects({}, serviceEventListeners);
}
