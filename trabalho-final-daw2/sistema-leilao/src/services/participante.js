import { http } from "./config"

export default {
    listar:()=>{
        return http.get('participantes')
    },

    salvar:(participante) =>{
        return http.post('participante', participante);
    },

    editar:(participante) => {
        return http.put('participante', participante)
    },

    remover:(participante) => {
        return http.delete('participante',{data:participante})
    }
    
}