import { server, token } from "../../env.js";
import { Subject } from "../models/Subject.js";
import api from "./api.service.js";
const { BehaviorSubject } = rxjs;

let fakeData = [{id: 'subject1', name: 'Subject1', group: 1}, {id: 'subject2', name: 'Subject2', group: 2}];

function subjectService() {
  const _subjects = new BehaviorSubject(null);
  const _subjects$ = _subjects.asObservable();

  let serviceEventListeners$ = {
    onFetching: ()=>{},
    onSuccess: ()=>{},
    onError: ()=>{},
  };

  const _params = {};

  /**
   * @param {Subject[]} data 
  */
  const _next = (data) => {
   _subjects.next(data);
  }

  /**
   * @param {(data: Subject[] | null) => any} callback
   * @returns {Subscription}
   */
  const subscribe = (callback) => {
    return _subjects$.subscribe(callback);
  }

  /**
   * 
   * @param {{
   * onFetching: () => void,
   * onSuccess: (data: any) => void,
   * onError: (error: any) => void}} serviceEventListeners 
   */
  const setServiceEventListeners = (serviceEventListeners) => {
    serviceEventListeners$ = {...serviceEventListeners$, ...serviceEventListeners};
  }

  const getSubjectFilters = (cb) => {
    serviceEventListeners$?.onFetching?.();
    api.get(server.url + '/subject')
    .then(subjectFilters => {
      cb(subjectFilters);
    })
    .catch(err => {
      console.log(err);
    })
  }

  /**
   * 
   * @param {*} patchParams 
   */
  const filterSubjects = (patchParams) => {
    const params = {..._params, ...patchParams};
    serviceEventListeners$?.onFetching?.();
    api.get(server.url + "/subjects", params)
    .then((data)=>{
      _next(data);
      serviceEventListeners$?.onSuccess?.(data);
    })
    .catch((error) => {
      // _next(null);
      serviceEventListeners$?.onError?.(error);
    });
    _next(fakeData)
  }

  return {subscribe, filterSubjects, setServiceEventListeners, getSubjectFilters}
}

export default subjectService();