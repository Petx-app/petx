import api from "./../../services/axios"

export const confirmEmail = async (data: any) => {
    try{
        await api.post('/usuario/confirmar/email', data)
    }catch(error){
        if (error.response && error.response.data) {
            const errorMessage = error.response.data.message;
            console.log(errorMessage)
            throw new Error(errorMessage);
        } else {
            console.error('Erro desconhecido:', error);
            throw new Error('Erro ao confirmar codigo');
          }
    }
}

export const cadastrar = async (data: any) => {
    console.log(data)
    try{
        await api.post('/usuario', data)
    }catch(error){
        if (error.response && error.response.data) {
            const errorMessage = error.response.data.message;
            console.log(errorMessage)
            throw new Error(errorMessage);
        } else {
            console.error('Erro desconhecido:', error);
            throw new Error('Erro ao confirmar codigo');
          }
    }
}