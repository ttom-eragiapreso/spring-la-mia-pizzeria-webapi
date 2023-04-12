console.log("ciao");

const { createApp } = Vue;

createApp({
  data() {
    return {
      message: "ciao vue",
      apiUrl: "http://localhost:8080/api/pizza",
      response: {},
      newPizzaDescription: "",
      newPizzaPrice: 0,
      keyword: ""
    };
  },

  methods: {
    loadData() {
      axios.get(this.apiUrl + `?q=${this.keyword}`).then((response) => {
        console.log(response);
        this.response = response;
      });
    },

    deletePizza(id) {
      axios.delete(this.apiUrl + `/${id}`).then((response) => {
        console.log(response);
        this.loadData();
      });
    },

    savePizza() {
      axios
        .post(this.apiUrl, {
          description: this.newPizzaDescription,
          price: this.newPizzaPrice
        })
        .then(() => this.loadData());
    }
  },

  mounted() {
    this.loadData();
  }
}).mount("#app");
