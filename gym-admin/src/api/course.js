import request from "@/utils/system/request";

export function saveProject(data) {
  return request({
    url: "/api/course/project",
    method: "post",
    data
  });
}

export function removeProject(id) {
  return request({
    url: `/api/course/project/${id}`,
    method: "delete"
  });
}

export function removeProjects(data) {
  return request({
    url: "/api/course/projects",
    method: "delete",
    data
  });
}

export function updateProject(data) {
  return request({
    url: "/api/course/project",
    method: "put",
    data
  });
}

export function getProject(id) {
  return request({
    url: `/api/course/project/${id}`,
    method: "get"
  });
}

export function listProjects(data, current, size) {
  return request({
    url: "/api/course/projects",
    method: "post",
    params: { current, size },
    data
  });
}

export function saveCourse(data) {
  return request({
    url: "/api/course/course",
    method: "post",
    data
  });
}

export function removeCourse(id) {
  return request({
    url: `/api/course/course/${id}`,
    method: "delete"
  });
}

export function removeCourses(data) {
  return request({
    url: "/api/course/courses",
    method: "delete",
    data
  });
}

export function updateCourse(data) {
  return request({
    url: "/api/course/course",
    method: "put",
    data
  });
}

export function getCourse(id) {
  return request({
    url: `/api/course/course/${id}`,
    method: "get"
  });
}

export function listCourses(data, current, size) {
  return request({
    url: "/api/course/courses",
    method: "post",
    params: { current, size },
    data
  });
}

export function saveCoach(data) {
  return request({
    url: "/api/course/coach",
    method: "post",
    data
  });
}

export function removeCoach(id) {
  return request({
    url: `/api/course/coach/${id}`,
    method: "delete"
  });
}

export function removeCoaches(data) {
  return request({
    url: "/api/course/coaches",
    method: "delete",
    data
  });
}

export function updateCoach(data) {
  return request({
    url: "/api/course/coach",
    method: "put",
    data
  });
}

export function getCoach(id) {
  return request({
    url: `/api/course/coach/${id}`,
    method: "get"
  });
}

export function listCoaches(data, current, size) {
  return request({
    url: "/api/course/coaches",
    method: "post",
    params: { current, size },
    data
  });
}
