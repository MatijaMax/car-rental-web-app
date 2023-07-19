Vue.component("Facilitypage", {
	data: function () {
		    return {
				facility: null,
				firstWordOfName: '',
				contents: '',
				isManagerPage: false,
				enableAddContent: false,
				selectedIndex: 0,
				user: '',
				jwtt: '',
				role: '',
				
				content: {
					brand: '',
					model: '',
					price: '',
					vehicleType: '',
					facilityId: '',
					vehicleKind: '',
					fuelType: '',
					consumption: '',
					numberOfDoors: '',
					capacity: '',
					description: '',
					photoUrl: '',
					status: '',
					deleted:false
				},
				newTraining: {
					
				},

				isEdit: false,
				
				currContentId: ''
				
		  }
	},
	template: ` 
<div>
	<div class="facilityPage-div" v-if="facility !== null">
		<div id="slideshowDiv">
			<div id="carouselExampleIndicators" class="carousel" data-bs-ride="carousel">
				<ol class="carousel-indicators">
					<li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"></li>
					<li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"></li>
					<li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"></li>
				</ol>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img class="d-block" :src="'images/' + this.firstWordOfName + '0.png'" alt="First slide">
					</div>
					<div class="carousel-item">
						<img class="d-block" :src="'images/' + this.firstWordOfName + '1.png'" alt="Second slide">
					</div>
					<div class="carousel-item">
						<img class="d-block" :src="'images/' + this.firstWordOfName + '2.png'" alt="Third slide">
					</div>
				</div>
				<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				</a>
				<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
				</a>
			</div>
		</div>
		<div class="infoDiv">
			<p id="separator"></p>
			<div id="titleDiv">
				<img :src="'images/' + facility.logoPath" />
				<div>
					<h1> {{ facility.name }} </h1>
					<p id="separator-title"></p>
					<div id="ratingDiv">
						<p id="sf-rating"> Rating: {{ facility.avgRating }} ★ </p>
						<p id="sf-status" v-if="facility.status"> Open </p>
						<p id="sf-status" style="background-color: #ff8d8d; border: thick solid #ff4747;" v-else> Closed </p>
					</div>
				</div>
			</div>
			
			
			<button type="button" class="btn btn-primary active" v-if="isManagerPage"  @click="addContentEnable">Add content</button>
			<div id="new-content-div" v-if="enableAddContent">
				<form>
					
					<table>
					
					<tbody>
						<tr v-if="isEdit">
					          <td colspan="2">
					            <div class="carousel-item active" >
					              <img class="d-block1 smaller-image" :src="'images/crusader0.png'" alt="First slide">
					            </div>
					          </td>
					        </tr>
						<tr>
							<td>
								<label for="name-i">Brand: </label>
								
							</td>
							<td>
								<input type="text" id="name-i" v-model="content.brand" :disabled="!isManagerPage"></input>
							</td>
						</tr>
						<tr>
							<td>
								<label for="name-i">Model: </label>
								
				
							</td>
							<td>
								<input type="text" id="name-i" v-model="content.model" :disabled="!isManagerPage">
							</td>
								
						</tr>
						<tr>
							<td>
								<label for="duration-i">Price: </label>
								
							</td>
							<td>
								<input type="text" id="duration-i" v-model="content.price" :disabled="!isManagerPage"></input>
							</td>
						</tr>
						<tr>
						  <td>
						    <label for="duration-i">Vehicle Type:</label>
						    
						  </td>
						  <td>
						  	<select class="custom-select" id="duration-i" v-model="content.vehicleType" :disabled="!isManagerPage">
						      <option value="CAR">Car</option>
						      <option value="VAN">Van</option>
						      <option value="MOBILEHOME">Mobilehome</option>
						    </select>
						   </td>
						</tr>
						<tr>
						  <td>
						    <label for="duration-i">Vehicle Kind:</label>
						    
						  </td>
						  <td>
						  	<select class="custom-select" id="duration-i" v-model="content.vehicleKind" :disabled="!isManagerPage">
						      <option value="AUTOMATIC">Automatic</option>
						      <option value="MANUAL">Manual</option>
						    </select>
						   </td>
						</tr>
						<tr>
						  <td>
						    <label for="duration-i">Fuel Type:</label>
						    
						  </td>
						  <td>
						  	<select class="custom-select" id="duration-i" v-model="content.fuelType" :disabled="!isManagerPage">
						      <option value="DIESEL">Diesel</option>
						      <option value="PETROL">Petrol</option>
						      <option value="HYBRID">Hybrid</option>
						      <option value="ELECTRIC">Electric</option>
						    </select>
						   </td>
						</tr>
						<tr>
							<td>
								<label for="duration-i">Consumption: </label>
								
							</td>
							<td>
								<input type="text" id="duration-i" v-model="content.consumption" :disabled="!isManagerPage"></input>
							</td>
						</tr>
						<tr>
							<td>
								<label for="duration-i">Number of doors: </label>
								
							</td>
							<td>
								<input type="text" id="duration-i" v-model="content.numberOfDoors" :disabled="!isManagerPage"></input>
							</td>
						</tr>
						<tr>
							<td>
								<label for="duration-i">Capacity: </label>
								
							</td>
							<td>
								<input type="text" id="duration-i" v-model="content.capacity" :disabled="!isManagerPage"></input>
							</td>
						</tr>
						<tr>
							<td>
								<label for="duration-i">Description: </label>
								
							</td>
							<td>
								<input type="text" id="duration-i" v-model="content.description" :disabled="!isManagerPage"></input>
							</td>
						</tr>
						
						
						<tr v-if="!isEdit">
							<td>
								<label for="choose-i">Add image: </label>
								
							</td>
							<td>
								<input type="file" id="choose-i" v-on:change="loadLogo" :disabled="!isManagerPage"></input>
							</td>
						</tr>
						
						
						<tr>
							<td colspan="2" style="text-align:center">
								<input type="submit" value="Post" @click="addContent"  class="btn btn-info" v-if="isManagerPage"></input>
								<input type="submit" value="Delete" @click="deleteContent"  class="btn btn-info" v-if="isManagerPage && isEdit"></input>
							</td>
						</tr>
						</tbody>
					</table>
				</form>
			</div>
			<h1>Vehicles in the offer:</h1>
			<h2 v-if="contents === null || contents === ''">There is no regular content</h2>
			<div class="horizontal-div" v-if="contents != null && contents != ''" style="flex-wrap: wrap;">
				<div v-for="(c, index) in contents" @click="enableEdit(c)">
					<p class="content-class-lead" v-if="index === 0"> {{ c.brand }}&nbsp;{{c.model}} </p>
					<p class="content-class" v-else> {{ c.brand }}&nbsp; {{ c.model }}</p>
				</div>
			</div>
			
			
			<div class="horizontal-div">
				<div id="workingHoursDiv">
					<h1>Working Hours:</h1>
					<p id="separator-hours"></p>
					<div class="daysDiv">
						<div class="days">
							<p>Monday:</p>
							<p>Tuesday:</p>
							<p>Wednesday:</p>
							<p>Thursday:</p>
							<p>Friday:</p>
							<p>Saturday:</p>
							<p>Sunday:</p>
						</div>
						<div class="hours" style="margin-left: auto; margin-right: 5px;">
							<p>{{ facility.workingHours }}</p>
							<p>{{ facility.workingHours }}</p>
							<p>{{ facility.workingHours }}</p>
							<p>{{ facility.workingHours }}</p>
							<p>{{ facility.workingHours }}</p>
							<p>{{ facility.workingHours }}</p>
							<p>{{ facility.workingHours }}</p>
						</div>
					</div>
				</div>
				<div id="locationDiv">
					<h1>Location:</h1>
					<p id="separator-hours"></p>
					<div id="address-div">
						<p style="font-size: 24px; font-weight: bold; padding: 0;">Address: </p>
						<p style="font-size: 24px; font-weight: 500; padding: 0; margin-left: 10px;">{{ facility.location.address }}</p>
					</div>
				</div>
			</div>
			
		</div>
		
		<comments></comments>
    </div>
</div>
`
	, 
	methods : {
		addContentEnable: function() {
			if(!this.enableAddContent) {
				this.enableAddContent = true;
				
					
				this.content.brand = '';
				this.content.model = '';
				this.content.price = '';
				this.content.vehicleType = '';
				this.content.facilityId = '';
				this.content.vehicleKind = '';
				this.content.fuelType = '';
				this.content.consumption = '';
				this.content.numberOfDoors = '';
				this.content.capacity = '';
				this.content.status = "AVAILABLE";
				this.content.description = '';
				this.content.photoUrl = '';
				this.content.deleted = false;
				
				//this.isTraining = false;
				this.isEdit = false;
				
				
				var toParse = localStorage.getItem('jwt');
				
				/*axios.get("/rest/users/coaches/", {
					headers: {Authorization: `Bearer ${JSON.parse(toParse).jwt}`}
				})
				.then(response => {
					this.allCoaches = response.data;
				});	*/	
			}
			else 
				this.enableAddContent = false;	
				
			
		},
		enableEdit: function(content) {
			//if(this.isManagerPage == false) return;
			this.currContentId = content.id;
			this.isEdit = true;
			this.enableAddContent = true;
			
			this.content.brand = content.brand;
			this.content.model = content.model;
			this.content.vehicleType = content.vehicleType;
			this.content.price = content.price;
			this.content.photoUrl = content.photoUrl;
			this.content.vehicleKind = content.vehicleKind;
			this.content.facilityId = content.facilityId;
			this.content.description = content.description;
			this.content.fuelType = content.fuelType;
			this.content.consumption = content.consumption;
			this.content.numberOfDoors = content.numberOfDoors;
			this.content.capacity = content.capacity;
			this.content.status = content.status;
			
			
			/*if(this.content.type === 'training1' || this.content.type === 'training2') {
				this.isTraining = true;
			}
			
			var toParse = localStorage.getItem('jwt');
				
				axios.get("/rest/users/coaches/", {
					headers: {Authorization: `Bearer ${JSON.parse(toParse).jwt}`}
				})
				.then(response => {
					this.allCoaches = response.data;
				});	
			
			*/
		}
		,
		loadLogo: function(event) {
			var files = event.target.files;
			this.content.photoUrl = files[0].name;
			console.log(this.content.photoUrl);
		},
		addContent: function(){
			event.preventDefault();
			
			if (this.content.brand === '') {
			  alert("Brand must be selected");
			  return;
			}
			
			if (this.content.model === '') {
			  alert("Model must be selected");
			  return;
			}
			
			if (this.content.price === '') {
			  alert("Price must be selected");
			  return;
			}
			
			if (this.content.vehicleType === '') {
			  alert("Vehicle type must be selected");
			  return;
			}
			
			if (this.content.vehicleKind === '') {
			  alert("Vehicle kind must be selected");
			  return;
			}
			
			if (this.content.fuelType === '') {
			  alert("Fuel type must be selected");
			  return;
			}
			
			if (this.content.consumption === '') {
			  alert("Consumption must be selected");
			  return;
			}
			
			if (this.content.capacity === '') {
			  alert("Capacity must be selected");
			  return;
			}
			
			if (this.content.numberOfDoors === '') {
			  alert("Number of doors must be selected");
			  return;
			}
			
			if (this.content.photoUrl === '') {
			  alert("Image must be selected");
			  return;
			}
			
			this.content.facilityId = this.facility.id;
			this.content.status = "AVAILABLE";
			
			
			
			var toParse = localStorage.getItem('jwt');
			if(!toParse){
				alert("ERROR USER NOT LOGGED IN");
				return;	
			}
			
			if(!this.isEdit){
				axios.post("/rest/facilities/newcontent/", this.content, {
					headers: {Authorization: `Bearer ${JSON.parse(toParse).jwt}`}					
				})
				.then(response => {
					console.log(response);
					router.push('/');
				});
			} else {
				axios.post("/rest/facilities/editcontent/", this.content, {
					params: {id: this.currContentId},
					headers: {Authorization: `Bearer ${JSON.parse(toParse).jwt}`}					
				})
				.then(response => {
					console.log(response);
					router.push('/');
				});
			}
			
		},
		deleteContent: function(){
			
			this.content.deleted = true;
			
			
			
			var toParse = localStorage.getItem('jwt');
			if(!toParse){
				alert("ERROR USER NOT LOGGED IN");
				return;	
			}
			
			if(!this.isEdit){
				axios.post("/rest/facilities/newcontent/", this.content, {
					headers: {Authorization: `Bearer ${JSON.parse(toParse).jwt}`}					
				})
				.then(response => {
					console.log(response);
					router.push('/');
				});
			} else {
				axios.post("/rest/facilities/editcontent/", this.content, {
					params: {id: this.currContentId},
					headers: {Authorization: `Bearer ${JSON.parse(toParse).jwt}`}					
				})
				.then(response => {
					console.log(response);
					router.push('/');
				});
			}
			
		},
		isSelected(index) {
			return index === this.selectedIndex;
		},
		setTraining: function(c) {
			this.content = c;
			console.log(this.content);
		},
		
		
	},
	mounted () {
		console.log(this.$route.params.facilityID);
		axios.get("/rest/facilities/getById/", {params: { id: this.$route.params.facilityID}})
		.then(response => {
			this.facility = response.data;
			this.firstWordOfName = (this.facility.name).substring(0, this.facility.name.indexOf(' '));

			if(this.firstWordOfName === '')
				this.firstWordOfName = this.facility.name;

			console.log(this.firstWordOfName);

			//this.contents = this.facility.content.split(',');
			
			axios.get("/rest/facilities/content/", {params: {content: this.facility.content}})
			.then(response => {
				this.contents = response.data;
			});
			
		});

		var toParse = localStorage.getItem('jwt');
		this.jwtt = JSON.parse(toParse).jwt;
		this.role = JSON.parse(toParse).role;
		
		if(toParse) {
			axios.get("/rest/users/managerfacility/", {params: {username: JSON.parse(toParse).username}})
				.then(response => {
					if(response.data != null && this.$route.params.facilityID === response.data) {
						this.isManagerPage = true;
					}
				});
		}

		this.user = this.username = JSON.parse(toParse).username;

		
	 }
});