import api from "@/services/axios";

const adminBaseUrl = "/admin";

async function requestWithBaseUrl({ url, ...options }) {
  return api.request({
    url: `${adminBaseUrl}${url}`,
    ...options,
  });
}

export const adminApi = {
  finalizarQRCode: async function (uuid: string) {
    await requestWithBaseUrl({
      url: `/qrcode/${uuid}`,
      method: "PUT",
    });
  },

  consultarQRCodeDisponiveis: async function () {
    const response = await requestWithBaseUrl({
      url: `/uuid-disponiveis`,
      method: "GET",
    });
    return response.data;
  },

  gerarQRCode: async function (qtd: number) {
    await requestWithBaseUrl({
      url: `/pet/${qtd}`,
      method: "POST",
    });
  },

  consultarRegistroPet: async function () {
    const response = await requestWithBaseUrl({
      url: `/dados-pets`,
      method: "GET",
    });
    return response.data;
  },
};
