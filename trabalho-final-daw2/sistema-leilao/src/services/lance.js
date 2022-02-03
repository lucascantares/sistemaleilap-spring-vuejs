import { http } from "./config"

export default {
    listar:(id)=>{
        return http.get('lances/' + id)
    },

    salvar:(lance) =>{
        return http.post('lance', lance);
        
    },

    editar:(lance) => {
        return http.put('lance', lance)
    },

    remover:(lance) => {
        return http.delete('lance',{data:lance})
    }
    
}