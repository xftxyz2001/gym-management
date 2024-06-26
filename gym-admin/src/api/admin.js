import request from "@/utils/system/request";

export function loginApi(data) {
  return request({
    url: "/api/admin/login",
    method: "post",
    data
  });
}

export function passwordChange(data) {
  return request({
    url: "/api/admin/password/change",
    method: "post",
    data
  });
}

export function getCoachLoginInfo(coachId) {
  return request({
    url: `/api/admin/coach/${coachId}`,
    method: "get"
  });
}

export function setCoachLoginInfo(data) {
  return request({
    url: "/api/admin/coach",
    method: "post",
    data
  });
}

export function getMenuApi() {
  return request({
    url: "/menu/list",
    method: "post",
    baseURL: "/mock"
  });
}
