import request from "@/utils/system/request";
export function saveReward(data) {
  return request({
    url: "/api/points/reward",
    method: "post",
    data
  });
}

export function removeReward(id) {
  return request({
    url: `/api/points/reward/${id}`,
    method: "delete"
  });
}

export function removeRewards(data) {
  return request({
    url: "/api/points/rewards",
    method: "delete",
    data
  });
}

export function updateReward(data) {
  return request({
    url: "/api/points/reward",
    method: "put",
    data
  });
}

export function getReward(id) {
  return request({
    url: `/api/points/reward/${id}`,
    method: "get"
  });
}

export function listRewards(data) {
  return request({
    url: "/api/points/rewards",
    method: "post",
    data
  });
}

export function exchangeReward(data) {
  return request({
    url: "/api/points/exchange",
    method: "post",
    data
  });
}

export function removeExchange(id) {
  return request({
    url: `/api/points/exchange/${id}`,
    method: "delete"
  });
}

export function removeExchanges(data) {
  return request({
    url: "/api/points/exchanges",
    method: "delete",
    data
  });
}

export function getExchange(id) {
  return request({
    url: `/api/points/exchange/${id}`,
    method: "get"
  });
}

export function listExchanges(data) {
  return request({
    url: "/api/points/exchanges",
    method: "post",
    data
  });
}
