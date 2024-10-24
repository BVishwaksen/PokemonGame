//creation of the vue application
const app = Vue.createApp({
    data() {
        return {
            pokemonOne: '',
            pokemonTwo: '',
            pokemonName: '________',
            count:'________',
            errorMessage: ''
        };
    },
    methods: {
        battle() {
            //checking the validity of the pokemon Values
            if(!this.pokemonOne && !this.pokemonTwo){
                this.errorMessage = "Pokemon values should not be empty";
                return;
            } else if(!this.pokemonOne) {
                this.errorMessage = "Pokemon One value should not be empty";
                return
            } else if(!this.pokemonTwo) {
                this.errorMessage = "Pokemon Two value should not be empty";
                return
            }
            else {
            //API request for performing battle
                        this.errorMessage = ""
                        fetch("/attack/"+this.pokemonOne + "/" + this.pokemonTwo)
                                        .then(response => response.json())
                                        .then(data => {
                                            if(data.hitPoints<0){
                                                this.errorMessage = "Some Error Occured. Please Check Your Data"
                                                this.pokemonName = "________";
                                                this.count = "________";
                                                return
                                            }
                                            this.pokemonName = data.winner;
                                            this.count = data.hitPoints;
                        })
            }
        }
    }
});

app.mount('#app');
