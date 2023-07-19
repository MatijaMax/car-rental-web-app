Vue.component("Allvehicles", {
	data: function(){
		return{
		vehicles: null,
		search: "",
		startDate: "",
		endDate: "",
		jwtt: '',
		startDate : '',
		endDate : '',
		showButton: false,
		Vehicle: {
			
			
			
			
		}
						
	  }
	},
	template: ` 
<div id = "mainpage-div">
		<div>
			<form>
				<div class="search-group">
					<div class="searchName-group">
						<input type="date" v-model = "startDate" placeholder="Input start date here" class="form-control" id="first-input"></input>
					</div>
					<div class="searchName-group">
						<input type="date" v-model = "endDate" placeholder="Input end date here" class="form-control" id="first-input"></input>
					</div>

					<input type="submit" value="Show" v-on:click = "searchVehicles" class="btn btn-primary"></input>
				</div>
			</form>

		</div>
	<div id = "so-div">
	<div v-for="(f, index) in vehicles" id="list-div1">
	
                <div class="img-div">
                   
                        <img :src="'images/' + f.photoUrl"/>
                    
                </div>
                <div id="info-div">
                
                    <div id="title-div" style="margin-bottom: 10px;font-size: 40px;font-weight: semibold; margin-left: 10px;">
                        
                            {{ f.brand }}&nbsp;{{f.model}} 
                    </div>
                    <hr class="separator"></hr>
                    <div id="type-content-div">
<p class="so-p" id="p-content" style="display: inline-block;">Price: {{f.price}}</p>
<button v-if="showButton" v-on:click="addToBasket(f)" class="btn btn-light btn-outline-dark" style="background-color: blue; color: white; font-weight: bold; display: inline-block;">Add to cart</button>
				</div>
				<div>
                    <p class="so-p" id="p-location"> Vehicle Type: {{f.vehicleType}} </p>
                    <p class="so-p" id="p-avgRating"> Vehicle Kind:  {{f.vehicleKind}}</p>
                    <p class="so-p" id="p-workingHours"> Fuel Type: {{f.fuelType}}</p>
                    <p class="so-p" id="p-workingHours"> Consumption: {{f.consumption}}</p>
                    <p class="so-p" id="p-workingHours"> Number of Doors: {{f.numberOfDoors}}</p>
                    <p class="so-p" id="p-workingHours"> Capacity: {{f.capacity}}</p>
                    <p class="so-p" id="p-workingHours"> Description: {{f.description}}</p>
                
                </div>
				
		</div
      
	</div>
</div>
	
	`
	,
	methods:{
		
			addToBasket(f){
				console.log(f);
				axios.post('rest/basket/newcontent/', f, {
				headers: { Authorization: `Bearer ${this.jwtt}` }
				
				
			})	
				.then(response => {
				 console.log("Before redirect");
				router.push(`/basket`);
				console.log("After redirect");
				location.reload(); // Reload the page after redirecting
				})
			},
		
		
			searchVehicles: function() {
			event.preventDefault();
			this.search = this.startDate + "," + this.endDate;
						
			if(this.search != ",") {
				axios.get("/rest/vehicles/search/",  {params: { filter: this.search}})
	    		.then(response => {
					this.vehicles = response.data;
					this.showButton = true;
					console.log(this.vehicles);
				}).catch(error => {
	    			console.log(error.response)
				});	
			} 
	
		},	
	},
	mounted(){
		
		var toParse = localStorage.getItem('jwt');
		var jwtt = '';
		if(!toParse)
			alert("ERROR USER NOT LOGGED IN");
		else 
			jwtt = JSON.parse(toParse).jwt;
		
		axios.get('/rest/vehicles/', {
				headers: {Authorization: `Bearer ${jwtt}`}
			})
			.then(response => {
				if(response.data === null) {
					alert("User session expired");
					localStorage.removeItem('jwt');
					this.$root.$emit('messageFromChild1ToChild2', 'false');
					router.push('/rest/vehicles');
				}
				this.vehicles = response.data;
				console.log(this.vehicles);
			});
	}
	
	
});