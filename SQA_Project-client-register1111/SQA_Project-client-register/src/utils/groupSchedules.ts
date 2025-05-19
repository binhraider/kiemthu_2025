import { ISchedule } from '../context/models/Schedule';

export function groupSchedules(schedules: ISchedule[]) {
  const groupedObjects: { [key: string]: ISchedule[] } = {};

  schedules.forEach((object) => {
    const key = `${object.studyDate.id}-${object.schoolShift.id}-${object.room.id}-${object.teacher.id}`;
    if (!groupedObjects[key]) {
      groupedObjects[key] = [];
    }
    groupedObjects[key].push(object);
  });

  const ans: ISchedule[] = Object.values(groupedObjects).map((schedules) => {
    const schedule = schedules?.[0];
    const lastSchedule = schedules?.[schedules.length - 1];
    const des =
      schedule.id === lastSchedule.id
        ? schedule.studyWeek.des
        : (schedule.studyWeek.des?.split(' - ')?.[0] ?? '') +
          ' - ' +
          lastSchedule.studyWeek.des?.split(' - ')?.[1];
    return {
      ...schedule,
      studyWeek: { ...schedule.studyWeek, des },
    } as ISchedule;
  });

  return ans;
}
