<template>
    <div class="container">
        <form @submit.prevent="salvar(), listar()">
            <div class="columns">
                <div class="column">
                    <label>ITEM</label>
                    <input v-validate="'required'" id="fieldItem" name="fieldItem" class="input" type="text" placeholder="Digite o nome do item" required v-model="leilao.item" >
                </div>
                <div class="column">
                    <label>DATA ABERTURA</label>
                    <input v-validate="'before:beforeTarget'" name="before_field" class="input" type="date" required v-model="leilao.dataAbertura" >
                </div>
                <div class="column">
                    <label>DATA ENCERRAMENTO</label>
                    <input name="before_field_target" ref="beforeTarget" class="input" type="date" required v-model="leilao.dataFechamento" >
                </div>
            </div>
            <label>LANCE MINIMO</label>
            <input class="input" type="text" placeholder="Valor minimo dos lances" required v-model="leilao.lanceMinimo">
          
            <div v-if="leilao.id">
                <label>SITUAÇÃO</label>
                <br>
                <div class="select">
                        <select v-model="leilao.status">
                            <option v-for="situacao in situacoes" v-bind:key="situacao">{{situacao}}</option>
                        </select>
                    </div>
                </div>
            <br>
            <div class="column">
                <button style="margin-top: 10px" class="button is-primary">Salvar<i class="material-icons left"></i></button>
                <button @click="limpar()" style="margin-top: 10px" class="button is-danger"><i class="material-icons">Cancelar</i></button>
            </div>
        </form>
        
        <label>FILTRAR POR SITUAÇÃO</label>
        <div style="margin-left: 10px" class="select">
            <select v-model="leilao.status">
                <option v-for="situacao in situacoes" v-bind:key="situacao">{{situacao}}</option>
            </select>
        </div>
        <button @click="listarPorStatus(leilao.status)" class="button is-dark">Filtrar</button>
        <button @click="listar()" class="button is-dark">Limpar Filtro</button>
        

        <table class="table">
            <thead>
                <tr>
                    <th>ITEM</th>
                    <th>DATA ABERTURA</th>
                    <th>DATA FECHAMENTO</th>
                    <th>LANCE MINIMO</th>
                    <th>SITUAÇÃO</th>
                </tr>
            </thead>

            <tbody>
                <tr v-for="leilao of leiloes" :key="leilao.id">
                    <td>{{ leilao.item }}</td>
                    <td>{{ leilao.dataAbertura }}</td>
                    <td>{{ leilao.dataFechamento }}</td>
                    <td>{{ leilao.lanceMinimo }}</td>
                    <td>{{ leilao.status }}</td>
                    <td>
                        <router-link
                            class="mr-3"
                            :to="{ name: 'lances', params: { id: leilao.id, lanceMinimo: leilao.lanceMinimo} }"
                            >
                            <button v-show="leilao.status == 'ABERTO'" class="button is-link">Dar Lances</button>
                            </router-link>
                        <button @click="editar(leilao), listar()" class="button is-success"><i class="material-icons">Editar</i></button>
                        <button @click="remover(leilao), listar()" class="button is-danger"><i class="material-icons">Excluir</i></button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import Leilao from '@/services/leilao'

export default {
    data(){
        return {
            leilao:{
                id: '',
                item: '',
                dataAbertura: '',
                lanceMinimo: '',
                lanceMaximo: '',
                dataFechamento: '',
                status: 'INATIVO',
            },
            leiloes:[],
            situacoes: ['INATIVO', 'EXPIRADO', 'ABERTO', 'FINALIZADO']
        }
    },
    mounted(){
        this.listar();
    },
    methods:{
        limpar(){
            this.leilao = {};
        },
        listarPorStatus(status){
            this.leiloes = {};
            Leilao.listarPorStatus(status).then(res =>{
                this.leiloes = res.data;
            })
        },
        listar(){
            Leilao.listar().then(res => {
                this.leiloes = res.data;
            })
        },
        salvar(){
            if(!this.leilao.id){
                Leilao.salvar(this.leilao);
                alert('Salvo com sucesso!');
                this.leilao = {};
            } else {
                Leilao.editar(this.leilao);
                alert('Editado com sucesso!');
                this.leilao = {}
            }
        },
        editar(leilao){
            this.leilao = leilao;
        },
        remover(leilao){
            Leilao.remover(leilao);
            alert('Removido com sucesso');
            this.listar();
        }
    }
}
</script>

<style>
.button{
    margin-left: 5px;
    margin-right: 5px;
}
</style>
