import request from "@/utils/system/request";

export function saveConsume(data) {
  return request({
    url: "/api/finance/consume",
    method: "post",
    data
  });
}

export function removeConsume(id) {
  return request({
    url: `/api/finance/consume/${id}`,
    method: "delete"
  });
}

export function removeConsumes(data) {
  return request({
    url: "/api/finance/consumes",
    method: "delete",
    data
  });
}

export function updateConsume(data) {
  return request({
    url: "/api/finance/consume",
    method: "put",
    data
  });
}

export function getConsume(id) {
  return request({
    url: `/api/finance/consume/${id}`,
    method: "get"
  });
}

export function listConsumes(data, current, size) {
  return request({
    url: "/api/finance/consumes",
    method: "post",
    params: { current, size },
    data
  });
}

export function refundConsume(data) {
  return request({
    url: "/api/finance/refund/b",
    method: "post",
    data
  });
}

export function saveRefund(data) {
  return request({
    url: "/api/finance/refund",
    method: "post",
    data
  });
}

export function removeRefund(id) {
  return request({
    url: `/api/finance/refund/${id}`,
    method: "delete"
  });
}

export function removeRefunds(data) {
  return request({
    url: "/api/finance/refunds",
    method: "delete",
    data
  });
}

export function updateRefund(data) {
  return request({
    url: "/api/finance/refund",
    method: "put",
    data
  });
}

export function getRefund(id) {
  return request({
    url: `/api/finance/refund/${id}`,
    method: "get"
  });
}

export function listRefunds(data, current, size) {
  return request({
    url: "/api/finance/refunds",
    method: "post",
    params: { current, size },
    data
  });
}
