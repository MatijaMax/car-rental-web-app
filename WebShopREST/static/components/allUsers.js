Vue.component("Allusers", {
	data: function () {
		    return {
			users: [],
			search: "",
			searchName: "",
			searchSurname: "",
			searchUsername: "",
			searchType2:"",
			searchRating:"",
			sortName: 0,
			sortSurname: 0,
			sortUsername: 0,
			sortRating: 0,
			userTypes: [],
			isSusChecked: false,
			userMedal: ["", "GOLD", "SILVER", "BRONZE"],
			searchType:"",
			selectedIndex: 0,
			jwtt: '',
			Facility: {
				name: 'Summer Wheels',
				status: false,
				content:'',
				location: {
					longitude: 1.0,
					latitude: 2.0,
					address: 'Bulevar Oslobodjenja 72, Novi Sad',
				},
				logoPath: 'image2.png',
				workingHours: '08:00-23:00',
				manager: ''
			}
			
			
		  }
	},
	template: ` 
<div>
		<div>
			<form>
				<div class="search-group">
					<div class="searchName-group">
						<input type="text" v-model = "searchName" placeholder="Name" class="form-control" id="first-input"></input>
						<button id="nameButton" v-on:click="sortBy" class="btn btn-light btn-outline-dark">A-Z</button>
					</div>
					<div class="searchType-group">
						<select v-model="searchType" class="form-select" aria-label="Default select example">
						  <option v-for="t in userTypes">{{t}}</option>
						</select>
					</div>
					<div class="searchType-group">
						<select v-model="searchType2" class="form-select" aria-label="Default select example">
						  <option v-for="t in userMedal">{{t}}</option>
						</select>
					</div>
					<div class="searchName-group">
						<input type="text" v-model = "searchRating" placeholder="Points" class="form-control"></input>
						<button id="ratingButton" v-on:click="sortBy" class="btn btn-light btn-outline-dark">0-MAX</button>
					</div>
					<div class="searchName-group">
						<input type="text" v-model = "searchSurname" placeholder="Surname" class="form-control"></input>
						<button id="surnameButton" v-on:click="sortBy" class="btn btn-light btn-outline-dark">A-Z</button>
					</div>
					<div class="searchLocation-group">
						<input type="text" v-model = "searchUsername" placeholder="Username" class="form-control"></input>
						<button id="usernameButton" v-on:click="sortBy" class="btn btn-light btn-outline-dark">A-Z</button>
					</div>

					<input type="submit" value="Search" v-on:click = "searchForFacility" class="btn btn-primary"></input>
				</div>
			</form>
			<!--<form>
				<div class="sort-group">
					<input type="text" v-model = "searchName" placeholder="Name" class="form-control" id="first-input"></input>
					<input type="text" v-model = "searchType" placeholder="Type" class="form-control"></input>
					<input type="text" v-model = "searchType2" placeholder="Type" class="form-control"></input>
					<input type="text" v-model = "searchRating" placeholder="Type" class="form-control"></input>
					<input type="text" v-model = "searchSurname" placeholder="Surname" class="form-control"></input>
					<input type="text" v-model = "searchUsername" placeholder="Username" class="form-control"></input>

					<input type="submit" value="Search" v-on:click = "searchForFacility" class="btn btn-primary"></input>
				</div>
			</form>-->
		</div>
		            <div>
			<label for="openSwitch" id="switchLabel">Show suspicious users:</label>
			<label class="switch" >
				<input type="checkbox" id="openSwitch" v-model="isSusChecked">
				<span class="slider round"></span>
			</label>
			
		</div>
	<div v-for="(u, index) in users" id="profiles-div" v-if="!(!u.sus && isSusChecked)">
	
			<div id="info-div">
				<div id="title-div"> 
						<h1>{{ u.name }} {{u.surname}}</h1>				
				</div> <div><button id="BlockButton" v-on:click="blockUser(u.username)" class="btn btn-light btn-outline-dark" style="background-color: red; color: white; font-weight: bold;">Block this user</button>
<label v-if="u.sus" style="font-weight: bold; color: red;">SUS</label>
<label v-else style="font-weight: bold; color: green;">GOOD</label>
				<hr class="separator"></hr>
				<!--<div id="type-content-div">
					<p class="so-p" id="p-content"> Content: {{ f.content }} </p>

				</div>-->

				<p class="so-p" id="p-location"> Username: {{ u.username }} </p>
				<p class="so-p" id="p-location"> Password: {{ u.password }} </p>
				<p class="so-p" id="p-workingHours"> Role: {{ u.role }} </p>
				<p class="so-p" id="p-workingHours"> Date of birth: {{ u.dateOfBirth }} </p>
				<p class="so-p" id="p-workingHours"> Gender: {{ u.gender }} </p>				
				<p class="so-p" id="p-workingHours"> Points: {{ u.points }} </p>
				
				
			</div>
			
		</div>
	
</div>
`
	, 
	methods : {
		searchForFacility: function() {
			event.preventDefault();
			this.search = this.searchName + "," + this.searchType + "," + this.searchType2 + "," + this.searchRating + "," + this.searchSurname + "," 
						+ this.searchUsername;
						
			if(this.search != ",,,,,") {
				axios.get("/rest/users/search/",  {params: { filter: this.search}})
	    		.then(response => {
					this.users = response.data
					this.users.sort(function(a,b) {
						return b.status - a.status;
					})
				}).catch(error => {
	    			console.log(error.response)
				});	
			} else {
				axios.get('rest/users/')
		        .then(response => {
					this.users = response.data
					this.users.sort(function(a,b) {
						return b.status - a.status;
					})

					this.sortName = this.buttonReset('nameButton', 'A-Z');
					this.sortSurname = this.buttonReset('surnameButton', 'A-Z');
					this.sortUsername = this.buttonReset('usernameButton', 'A-Z');
					this.sortRating = this.buttonReset('ratingButton', 'A-Z');
				})
			}
	
		},
		isSelected(index) {
			return index === this.selectedIndex;
		},
		sortBy: function() {
			event.preventDefault();
			switch(window.event.target.id) {
				case 'nameButton':
					this.sortName = this.sortToggler(this.sortName, window.event.target, 'A-Z', 'Z-A');

					this.sortSurname = this.buttonReset('surnameButton', 'A-Z');
					this.sortUsername = this.buttonReset('usernameButton', 'A-Z');
					this.sortRating = this.buttonReset('ratingButton', 'A-Z');

					this.facilitySort(this.sortName, this.sortSurname, this.sortUsername, this.sortRating);
					break;
				case 'surnameButton':
					this.sortSurname = this.sortToggler(this.sortSurname, window.event.target, 'A-Z', 'Z-A');

					this.sortName = this.buttonReset('nameButton', 'A-Z');
					this.sortUsername = this.buttonReset('usernameButton', 'A-Z');
					this.sortRating = this.buttonReset('ratingButton', 'A-Z');

					this.facilitySort(this.sortName, this.sortSurname, this.sortUsername, this.sortRating);
					break;
				case 'usernameButton':
					this.sortUsername = this.sortToggler(this.sortUsername, window.event.target, 'A-Z', 'Z-A');

					this.sortName = this.buttonReset('nameButton', 'A-Z');
					this.sortSurname = this.buttonReset('surnameButton', 'A-Z');
					this.sortRating = this.buttonReset('ratingButton', 'A-Z');

					this.facilitySort(this.sortName, this.sortSurname, this.sortUsername, this.sortRating);
					break;
				case 'ratingButton':
					this.sortRating = this.sortToggler(this.sortRating, window.event.target, 'A-Z', 'Z-A');

					this.sortName = this.buttonReset('nameButton', 'A-Z');
					this.sortSurname = this.buttonReset('surnameButton', 'A-Z');
					this.sortUsername = this.buttonReset('usernameButton', 'A-Z');

					this.facilitySort(this.sortName, this.sortSurname, this.sortUsername, this.sortRating);
					break;
			}

		},
		sortToggler: function(sorty, trigger, ascending, descending) {
			event.preventDefault();
			++sorty;

			if(sorty === 3) {
				sorty = 0;
				trigger.setAttribute('class', 'btn btn-light btn-outline-dark');
				trigger.textContent = ascending;
				return sorty;
			}

			switch(sorty) {
				case 0:
					trigger.setAttribute('class', 'btn btn-light btn-outline-dark');
					trigger.textContent = ascending;
					break;
				case 1:
					trigger.setAttribute('class', 'btn btn-primary');
					trigger.textContent = ascending;
					break;
				case 2:
					trigger.textContent = descending;
					break;
			}
			return sorty;
		},
		facilitySort: function(sortName, sortSurname, sortUsername, sortRating) {
			switch(sortUsername) {
				case 0:
					if(sortName === 0 && sortSurname === 0 && sortRating ===0)
						this.searchForFacility();
					break;
				case 1:
					this.users.sort((a, b) => a.username.localeCompare(b.username));
					break;
				case 2:
					this.users.sort((a, b) => b.username.localeCompare(a.username));
					break;
			}

			switch(sortSurname) {
				case 0:
					if(sortName === 0 && sortUsername === 0 && sortRating ===0)
						this.searchForFacility();
					break;
				case 1:
					this.users.sort((a, b) => a.surname.localeCompare(b.surname));
					break;
				case 2:
					this.users.sort((a, b) => b.surname.localeCompare(a.surname));
					break;
			}

			switch(sortName) {
				case 0:
					if(sortSurname === 0 && sortUsername === 0 && sortRating ===0)
						this.searchForFacility();
					break;
				case 1:
					this.users.sort((a, b) => a.name.localeCompare(b.name));
					break;
				case 2:
					this.users.sort((a, b) => b.name.localeCompare(a.name));
					break;
			}
			switch(sortRating) {
				case 0:
					if(sortName === 0 && sortUsername === 0 && sortSurname ===0)
						this.searchForFacility();
					break;
				case 1:
					this.users.sort((a, b) => b.points - a.points);
					break;
				case 2:
					this.users.sort((a, b) => a.points - b.points);
					break;
			}
		},
		blockUser: function(u){
			event.preventDefault();
			this.Facility.manager = u;
			this.Facility.status = true;
			//console.log(this.Facility);
			console.log(this.Facility.manager);
			//console.log(this.Facility.status);
			
			
			
			axios.post('/rest/facilities/block/', this.Facility, {
				headers: { Authorization: `Bearer ${this.jwtt}` }
			})			
			.then(response => {
				 console.log("Before redirect");
				router.push(`/`);
				console.log("After redirect");
			})
						
		},
		
		
		
		buttonReset: function(buttonId, ascending) {
  const button = document.getElementById(buttonId);
  if (button) {
    button.setAttribute('class', 'btn btn-light btn-outline-dark');
    button.textContent = ascending;
  } else {
    console.error(`Button element with ID '${buttonId}' not found.`);
  }
  return 0;
},
		
		
	},
	mounted () {
		var toParse = localStorage.getItem('jwt');
		var jwtt = '';
		if(!toParse)
			alert("ERROR USER NOT LOGGED IN");
		else 
			jwtt = JSON.parse(toParse).jwt;
		
		axios.get('/rest/users/', {
				headers: {Authorization: `Bearer ${jwtt}`}
			})
			.then(response => {
				if(response.data === null) {
					alert("User session expired");
					localStorage.removeItem('jwt');
					this.$root.$emit('messageFromChild1ToChild2', 'false');
					router.push('/');
				}
				this.users = response.data;
				//console.log(this.users);
			});
		axios.get('rest/users/types/')
			.then(response => {
				this.userTypes = response.data;
			});
    }
});