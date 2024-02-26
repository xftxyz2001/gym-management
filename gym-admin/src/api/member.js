import request from "@/utils/system/request";

export function saveMember(data) {
  return request({
    url: "/api/member/member",
    method: "post",
    data
  });
}

export function removeMember(id) {
  return request({
    url: `/api/member/member/${id}`,
    method: "delete"
  });
}

export function removeMembers(data) {
  return request({
    url: "/api/member/members",
    method: "delete",
    data
  });
}

export function updateMember(data) {
  return request({
    url: "/api/member/member",
    method: "put",
    data
  });
}

export function getMember(id) {
  return request({
    url: `/api/member/member/${id}`,
    method: "get"
  });
}

export function listMembers(data, current, size) {
  return request({
    url: "/api/member/members",
    method: "post",
    params: { current, size },
    data
  });
}

export function saveCard(data) {
  return request({
    url: "/api/member/card",
    method: "post",
    data
  });
}

export function removeCard(id) {
  return request({
    url: `/api/member/card/${id}`,
    method: "delete"
  });
}

export function removeCards(data) {
  return request({
    url: "/api/member/cards",
    method: "delete",
    data
  });
}

export function updateCard(data) {
  return request({
    url: "/api/member/card",
    method: "put",
    data
  });
}

export function getCard(id) {
  return request({
    url: `/api/member/card/${id}`,
    method: "get"
  });
}

export function listCards(data, current, size) {
  return request({
    url: "/api/member/cards",
    method: "post",
    params: { current, size },
    data
  });
}
