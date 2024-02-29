import request from "@/utils/system/request";

export function memberStatistics(data) {
  return request({
    url: "/api/statistics/member",
    method: "post",
    data
  });
}

export function cardStatistics(data) {
  return request({
    url: "/api/statistics/card",
    method: "post",
    data
  });
}

export function courseStatistics(data) {
  return request({
    url: "/api/statistics/course",
    method: "post",
    data
  });
}

export function incomeStatistics(data) {
  return request({
    url: "/api/statistics/income",
    method: "post",
    data
  });
}
