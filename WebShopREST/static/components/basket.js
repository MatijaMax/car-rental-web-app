Vue.component("Basket", {
  data: function() {
    return {
      vehicles: [],
      basket: null,
      jwtt: '',
      Vehicle: {},
      Basket: {
        username: '',
        price: 0.0,
        context: '',
        discount : 0.0,
        totalPrice : 0.0
      },
      
      

		  
 		Order: {
		  vehicles: '',
		  facility: '1',
		  date: '03.07.2023.',
		  time: '12:00 ',
		  leaseDuration: 48,
		  price: 0.0,
		  username: 'XX',
		  name: 'YY',
		  surname: 'ZZ',
		  status: 'PENDING',
	},
	
		profileData: {
			username : '',
		 	password : '',
			name : '',
			surname : '',
			gender : '',
			dateOfBirth : '',
			role : '',
			points : 0,
			customerType : ''
		},
		  
	  
      
      
      
      
      
      
    }
  },
  template: ` 
    <div id="mainpage-div">
      <div>
        <form>
          <div >
            <input type="submit" v-on:click="makeOrder" value="MAKE ORDER" class="btn btn-primary btn-lg"></input>
            <label style="margin-left: 10px; font-weight: bold; font-size: 1.2em;">BASKET PRICE: {{ Basket.price }}</label>
            <label style="margin-left: 10px; font-weight: bold; font-size: 1.2em;" v-if="profileData.customerType === 'SILVER'">DISCOUNT: {{ Basket.price * 0.03 }}</label>
            <label style="margin-left: 10px; font-weight: bold; font-size: 1.2em;" v-if="profileData.customerType === 'SILVER'">TOTAL PRICE: {{ Basket.price - Basket.price*0.03 }}</label>
            <label style="margin-left: 10px; font-weight: bold; font-size: 1.2em;" v-if="profileData.customerType === 'GOLD'">DISCOUNT: {{ Basket.price * 0.05 }}</label>
            <label style="margin-left: 10px; font-weight: bold; font-size: 1.2em;" v-if="profileData.customerType === 'GOLD'">TOTAL PRICE: {{ Basket.price - Basket.price*0.05 }}</label>
          </div>
        </form>
      </div>
      <div id="so-div">
        <div v-for="(vehicle, index) in vehicles" :key="index" id="list-div1">
          <div class="img-div">
            <img :src="'images/' + vehicle.photoUrl"/>
          </div>
          <div id="info-div">
            <div id="title-div" style="margin-bottom: 10px;font-size: 40px;font-weight: semibold; margin-left: 10px;">
              {{ vehicle.brand }}&nbsp;{{ vehicle.model }}
            </div>
            <hr class="separator"></hr>
            <div id="type-content-div">
              <p class="so-p" id="p-content"> Price: {{ vehicle.price }}</p>
            </div>
            <p class="so-p" id="p-location"> Vehicle Type: {{ vehicle.vehicleType }} </p>
            <p class="so-p" id="p-avgRating"> Vehicle Kind:  {{ vehicle.vehicleKind }}</p>
            <p class="so-p" id="p-workingHours"> Fuel Type: {{ vehicle.fuelType }}</p>
            <p class="so-p" id="p-workingHours"> Consumption: {{ vehicle.consumption }}</p>
            <p class="so-p" id="p-workingHours"> Number of Doors: {{ vehicle.numberOfDoors }}</p>
            <p class="so-p" id="p-workingHours"> Capacity: {{ vehicle.capacity }}</p>
            <p class="so-p" id="p-workingHours"> Description: {{ vehicle.description }}</p>
        <p>Profile Points: {{ profileData.points }}</p>
          </div>
          <div style="margin-top: 180px;">
  <button class="btn btn-success" @click="decrementQuantity(vehicle)" style="background-color: gray; width: 35px; color: white; font-weight: bold; margin-bottom: 10px; margin-right: 0px;">-</button>
  <span style="font-weight: bold; margin-bottom: 10px; margin-right: 0px;">{{ vehicle.quantity }}</span>
  <button class="btn btn-success" @click="incrementQuantity(vehicle)" style="background-color: grey; width: 35px; color: white; font-weight: bold; margin-bottom: 10px; margin-right: 0px;">+</button>
  <button class="btn btn-light btn-outline-dark" @click="removeAllFromBasket(vehicle)" style="background-color: red; color: white; font-weight: bold; margin-bottom: 20px; margin-right: 0px;">Remove</button>
</div>


        </div>
      </div>
    </div>
  `,
  methods: {
	 
	  makeOrder() {
		   this.updateTotalPrice();
  console.log(this.Order);
  axios
    .post('rest/order/new/', this.Order, {
      headers: { Authorization: `Bearer ${this.jwtt}` }
    })
    .then(response => {
      console.log("Before redirect");
      router.push(`/`);
      this.updateTotalPrice();
      console.log("After redirect");
      // location.reload(); // Reload the page after redirecting
    })
    .then(() => {
		this.updateTotalPrice();
      console.log(this.profileData.points);
      this.profileData.points += Math.round(this.Basket.price / 1000 * 133);
      console.log(this.profileData.points);
      console.log(this.profileData.customerType);
      if(this.profileData.points>500 && this.profileData.points<1000){
		  this.profileData.customerType = "SILVER";
	  } else
	  if(this.profileData.points>1000){
		  this.profileData.customerType = "GOLD";
	  } else {
		  this.profileData.customerType = "BRONZE";
	  }
	  console.log(this.profileData.customerType);
      axios
        .post('/rest/users/edit/', this.profileData, {
          headers: { Authorization: `Bearer ${this.jwtt}` },
        })
        .then((response) => {
          console.log('User points increased:', response.data);
        })
        .catch((error) => {
          console.error('Failed to update user points:', error);
        });
    });
},
	  
	  removeAllFromBasket(vehicle) {
      console.log(vehicle);
      axios.post('rest/basket/removeAllContent/', vehicle, {
          headers: { Authorization: `Bearer ${this.jwtt}` }
        })
        .then(response => {
          console.log("Before redirect");
          router.push(`/basket`);
          console.log("After redirect");
          location.reload(); // Reload the page after redirecting
        });
    },
    removeFromBasket(vehicle) {
      console.log(vehicle);
      axios.post('rest/basket/removecontent/', this.Order, {
          headers: { Authorization: `Bearer ${this.jwtt}` }
        })
        .then(response => {
          console.log("Before redirect");
          router.push(`/basket`);
          console.log("After redirect");
          location.reload(); // Reload the page after redirecting
        });
    },
    incrementQuantity(vehicle) {
      vehicle.quantity++;
      this.addToBasket(vehicle);
      this.updateTotalPrice();
    },
    decrementQuantity(vehicle) {
      if (vehicle.quantity > 1) {
        vehicle.quantity--;
        this.removeFromBasket(vehicle);
        this.updateTotalPrice();
      } 
    },
    addToBasket(vehicle) {
      console.log(vehicle);
      axios.post('rest/basket/newcontent/', vehicle, {
          headers: { Authorization: `Bearer ${this.jwtt}` }
        })
        .then(response => {
          console.log("Before redirect");
          router.push(`/basket`);
          console.log("After redirect");
          location.reload(); // Reload the page after redirecting
        });
    },
    updateTotalPrice() {
      this.Basket.price = this.vehicles.reduce((total, vehicle) => total + (vehicle.price * vehicle.quantity), 0);
		  //this.Order.price = this.Basket.price;
		
		  if (this.profileData.customerType === 'SILVER') {
		    this.Basket.discount = this.Basket.price * 0.03;
		    this.Basket.totalPrice = this.Basket.price - this.Basket.discount;
		  } else if(this.profileData.customerType === 'GOLD'){
			  this.Basket.discount = this.Basket.price * 0.05;
		    this.Basket.totalPrice = this.Basket.price - this.Basket.discount;
		  }
		   else {
		    this.Basket.discount = 0;
		    this.Basket.totalPrice = this.Basket.price;
		  }
		this.Order.price = this.Basket.totalPrice;
		console.log(this.Order.price);
    }
  },
  mounted() {
	  	  
    var toParse = localStorage.getItem('jwt');
    var jwtt = '';
    var usernamee = '';
    if (!toParse)
      alert("ERROR USER NOT LOGGED IN");
    else
      jwtt = JSON.parse(toParse).jwt;
		usernamee = JSON.parse(toParse).username;
    axios.get('/rest/basket/', {
        headers: { Authorization: `Bearer ${jwtt}` }
      })
      .then(response => {
        if (response.data === null) {
          alert("User session expired");
          localStorage.removeItem('jwt');
          this.$root.$emit('messageFromChild1ToChild2', 'false');
          router.push('/rest/basket');
        }
        this.basket = response.data;
		this.Order.vehicles=this.Basket.context;
        axios.get("/rest/facilities/content/", { params: { content: this.basket.content } })
          .then(response => {
            const vehiclesData = response.data;
            const uniqueVehicles = [];

            vehiclesData.forEach(vehicle => {
              const existingVehicle = uniqueVehicles.find(v => v.brand === vehicle.brand && v.model === vehicle.model);
              if (existingVehicle) {
                existingVehicle.quantity++;
              } else {
                uniqueVehicles.push({
                  ...vehicle,
                  quantity: 1
                });
              }
            });

            this.vehicles = uniqueVehicles;
            this.Order.vehicles = uniqueVehicles;
					 axios.get("/rest/users/getData/",  {
				params: { username: usernamee},
				headers: {Authorization: `Bearer ${jwtt}`}
			})
	          .then(response => {
				if(response.data === null) {
					alert("User session expired");
					localStorage.removeItem('jwt');
					this.$root.$emit('messageFromChild1ToChild2', 'false');
					router.push('/');
				}	
				
				this.profileData = response.data;
				
      		this.Order.username=this.profileData.username;
      		this.Order.name=this.profileData.name;
      		this.Order.surname=this.profileData.surname;
      		this.Order.vehicles=this.basket.content
			});
            // Calculate the total price by iterating over the vehicles
            this.updateTotalPrice();
          });
      });
  }
});
