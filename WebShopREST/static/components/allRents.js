Vue.component("Allrents", {
  data: function() {
    return {
      vehicles: null,
      role: "",
      orders: null,
      search: "",
      startDate: "",
      endDate: "",
      jwtt: '',
      facility: '',
      startPrice: '',
      endPrice: '',
      startDate: '',
      endDate: '',
      showButton: false,
      Vehicle: {},
      reason: '', // New property to track reason input
      
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
          <div class="search-group">
            <div class="searchName-group">
              <input type="text" v-model="facility" placeholder="Input facility here" class="form-control" id="first-input"></input>
              <button id="nameButton" class="btn btn-light btn-outline-dark">Namesort</button>
            </div>
            <div class="searchName-group">
              <input type="text" v-model="startPrice" placeholder="Input start price here" class="form-control" id="first-input"></input>
            </div>
            <div class="searchName-group">
              <input type="text" v-model="endPrice" placeholder="Input end price here" class="form-control" id="first-input"></input>
              <button id="nameButton" class="btn btn-light btn-outline-dark">Pricesort</button>
            </div>
            <div class="searchName-group">
              <input type="date" v-model="startDate" placeholder="Input start date here" class="form-control" id="first-input"></input>
            </div>
            <div class="searchName-group">
              <input type="date" v-model="endDate" placeholder="Input end date here" class="form-control" id="first-input"></input>
              <button id="nameButton" class="btn btn-light btn-outline-dark">Datesort</button>
            </div>
            <input type="submit" value="Show" v-on:click="searchVehicles" class="btn btn-primary"></input>
          </div>
        </form>
      </div>
      <div id="so-div">
        <div v-for="(f, index) in orders" id="list-div2">
          <div id="info-div">
            <div id="title-div" style="margin-bottom: 10px;font-size: 40px;font-weight: semibold; margin-left: 10px;">
              {{ f.name }}&nbsp;{{f.surname}}
            </div>
            <hr class="separator"></hr>
            <div id="type-content-div">
              <p class="so-p" id="p-content" style="display: inline-block;">Price: {{f.price}}</p>
              <button v-on:click="cancelOrder(f)" v-if="role === 'CUSTOMER' && f.status === 'PENDING'" class="btn btn-light btn-outline-dark" style="background-color: red; color: white; font-weight: bold; display: inline-block;">Cancel</button>
              <button v-if="role === 'MANAGER' && f.status === 'PENDING'" v-on:click="rejectOrder(f)" :disabled="isReasonEmpty" class="btn btn-light btn-outline-dark" style="background-color: red; color: white; font-weight: bold; display: inline-block;"> Reject</button>
              <button v-if="role === 'MANAGER' && f.status === 'PENDING'" v-on:click="approveOrder(f)" class="btn btn-light btn-outline-dark" style="background-color: green; color: white; font-weight: bold; display: inline-block;"> Approve</button>
              <button v-if="role === 'MANAGER' && f.status === 'APPROVED'" v-on:click="retrieveOrder(f)"  class="btn btn-light btn-outline-dark" style="background-color: blue; color: white; font-weight: bold; display: inline-block;"> Mark as Retrieved</button>
              <button v-if="role === 'MANAGER' && f.status === 'RETRIEVED'" v-on:click="returnOrder(f)"  class="btn btn-light btn-outline-dark" style="background-color: blue; color: white; font-weight: bold; display: inline-block;"> Mark as Returned</button>
              <input type="text" v-if="role === 'MANAGER' && f.status === 'PENDING'" v-model="reason" placeholder="Enter reason" class="form-control"></input>
            </div>
            <div>
              <p class="so-p" id="p-location"> Vehicles: {{f.vehicles}} </p>
              <p class="so-p" id="p-avgRating"> Date:  {{f.date}}</p>
              <p class="so-p" id="p-workingHours"> Time: {{f.time}} "previse precizno :)"</p>
              <p class="so-p" id="p-workingHours"> leaseDuration: {{f.leaseDuration}} h</p>
              <p class="so-p" id="p-workingHours"> Username: {{f.username}}</p>
              <p class="so-p" id="p-workingHours"> STATUS: {{f.status}}</p>
            </div>
          </div>
        </div>
      </div>
      <div>
        <input type="text" v-model="reason" placeholder="Enter reason" class="form-control"></input>
      </div>
    </div>
  `,
  methods: {
    cancelOrder(f) {
      console.log(f);
      const formattedTime = f.time.hour.toString() + ':' + f.time.minute.toString();
      delete f.time;
      f.time = formattedTime;
      console.log(f);
      axios.post('rest/order/canceled/', f, {
          headers: { Authorization: `Bearer ${this.jwtt}` }
        })
        .then(response => {
          console.log("Before redirect");
          router.push(`/`);
          console.log("After redirect");
          //location.reload(); // Reload the page after redirecting
        })
        .then(() => {
			
			
			
			
		      axios.get("/rest/users/getData/",  {
				params: { username: usernamee},
				headers: {Authorization: `Bearer ${jwtt}`}
			})
	          .then(response => {
				if(response.data === null) {
					alert("User session expired");
					localStorage.removeItem('jwt');
					this.$root.$emit('messageFromChild1ToChild2', 'false');
					
				}	
				
				this.profileData = response.data;	
			
			});  }).then(() => {
			
			
			
      console.log(this.profileData.points);
      this.profileData.points -= Math.round(f.price / 1000 * 133 * 4);
      if(this.profileData.points<0){
		  this.profileData.points=0;
	  }
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
       
       //location.reload();
    },
    retrieveOrder(f) {
      console.log(f);
      const formattedTime = f.time.hour.toString() + ':' + f.time.minute.toString();
      delete f.time;
      f.time = formattedTime;
      console.log(f);
      axios.post('rest/order/retrieved/', f, {
          headers: { Authorization: `Bearer ${this.jwtt}` }
        })
        .then(response => {
          console.log("Before redirect");
          router.push(`/allRents`);
          console.log("After redirect");
          location.reload(); // Reload the page after redirecting
        });
    },
    returnOrder(f) {
      console.log(f);
      const formattedTime = f.time.hour.toString() + ':' + f.time.minute.toString();
      delete f.time;
      f.time = formattedTime;
      console.log(f);
      axios.post('rest/order/returned/', f, {
          headers: { Authorization: `Bearer ${this.jwtt}` }
        })
        .then(response => {
          console.log("Before redirect");
          router.push(`/allRents`);
          console.log("After redirect");
          location.reload(); // Reload the page after redirecting
        });
    },
    rejectOrder(f) {
      console.log(f);
      const formattedTime = f.time.hour.toString() + ':' + f.time.minute.toString();
      delete f.time;
      f.time = formattedTime;
      console.log(f);
      axios.post('rest/order/rejected/', f, {
          headers: { Authorization: `Bearer ${this.jwtt}` }
        })
        .then(response => {
          console.log("Before redirect");
          router.push(`/allRents`);
          console.log("After redirect");
          location.reload(); // Reload the page after redirecting
        });
    },
    approveOrder(f) {
      console.log(f);
      const formattedTime = f.time.hour.toString() + ':' + f.time.minute.toString();
      delete f.time;
      f.time = formattedTime;
      console.log(f);
      axios.post('rest/order/approved/', f, {
          headers: { Authorization: `Bearer ${this.jwtt}` }
        })
        .then(response => {
          console.log("Before redirect");
          router.push(`/allRents`);
          console.log("After redirect");
          location.reload(); // Reload the page after redirecting
        });
    },
    searchVehicles: function() {
      // Rest of the code...
    },
  },
  computed: {
    isReasonEmpty() {
      return this.reason.trim() === '';
    }
  },
  mounted() {
	  var usernamee = '';
    this.$root.$on('messageFromChild2ToChild1', (text) => {
      if (text === 'false') {
        this.role = '';
      } else {
        var toParsee = localStorage.getItem('jwt');
        var rolee;
        if (!toParsee)
          rolee = ''
        else
          rolee = JSON.parse(toParse).role;
        this.role = rolee;
      }
    });

    var toParse = localStorage.getItem('jwt');
    var role;
    
    if (!toParse)
      role = ''
    else
      role = JSON.parse(toParse).role;
    this.role = role;

    console.log(this.role);
    var jwtt = '';
    if (!toParse)
      alert("ERROR USER NOT LOGGED IN");
    else
      jwtt = JSON.parse(toParse).jwt;
		usernamee = JSON.parse(toParse).username;
		
    axios.get('/rest/orders/', {
        headers: { Authorization: `Bearer ${jwtt}` }
      })
      .then(response => {
        if (response.data === null) {
          alert("User session expired");
          localStorage.removeItem('jwt');
          this.$root.$emit('messageFromChild1ToChild2', 'false');
          router.push('/rest/vehicles');
        }
        this.orders = response.data;
        console.log(this.orders);
      
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
				
			});
			
			
      console.log(this.profileData);   
      if(role === 'CUSTOMER'){
		  this.orders = this.orders.filter(order => order.username === usernamee);
	  }
	  
	 /* if(role === 'MANAGER'){
		      axios.get('/rest/orders/manager', {
        			headers: { Authorization: `Bearer ${jwtt}` }
      				})
      				.then(response => {
        			if (response.data === null) {
         	 			alert("User session expired");
          				localStorage.removeItem('jwt');
          				this.$root.$emit('messageFromChild1ToChild2', 'false');
          				router.push('/');
        				}
        				this.orders = response.data;
	  
	  		});
	  
	  
	  } */
      
      
      });
      
  }
});
