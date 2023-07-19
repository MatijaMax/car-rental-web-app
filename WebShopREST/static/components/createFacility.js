
Vue.component("Createfacility", {
	data: function () {
		    return {
			managers: null,
			map: null,
			selectedIndex: 0,
			jwtt: '',
			carFacility: {
				name: '',
				status: false,
				content:'',
				location: {
					longitude: 0.0,
					latitude: 0.0,
					address: '',
				},
				logoPath: '',
				workingHours: '',
				manager: ''
			},
			
		  }
	},
	template: ` 
<div>
	<div id="createFacility-div">

		<form>
			<table>
				<tr>
					<td>
						<p>Name: </p>
						<input type="text" v-model = "carFacility.name"></input>
					</td>
				</tr>
				<tr>
					<td>
						<p>Location: </p>
						<input type="text" v-model = "carFacility.location.address"></input>
					</td>
				</tr>
				<tr>
					<td>
						<p>Working time: </p>
						<input type="text" v-model = "carFacility.workingHours"></input>
					</td>
				</tr>
				<tr>
					<td>
						<p>Choose Logo: </p>
						<input type="file" v-on:change="loadLogo"></input>
					</td>
				</tr>
				<tr>
					<div class="list-group">
					<a href="#" class="list-group-item list-group-item-action" v-if="!managers.length" v-on:click="openNewPage">Create Manager</a>
						<a href="#" class="list-group-item list-group-item-action" v-for="(m, index) in this.managers" 
						v-on:click="setManager(m.username); selectedIndex = index;" v-bind:class="{ 'active' : isSelected(index) }">{{ m.name }} {{ m.surname }}</a>
					</div>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value="Create" v-on:click = "createFacility" class="btn btn-info"></input>
					</td>
				</tr>
			</table>
		</form>




       </div>

</div>
`
	, 
	methods : {
		loadLogo: function(event) {
			var files = event.target.files;
			this.carFacility.logoPath = files[0].name;
			console.log(this.carFacility.logoPath);
		},
		validateWorkingHours: function() {
		    const pattern = /^\d{2}:\d{2}-\d{2}:\d{2}$/;
		    const workingHours = this.carFacility.workingHours;
		
		    if (!pattern.test(workingHours)) {
		      return false;
		    }
		
		    const [startTime, endTime] = workingHours.split('-');
		    const [startHour, startMinute] = startTime.split(':');
		    const [endHour, endMinute] = endTime.split(':');
		
		    if (
		      isNaN(startHour) ||
		      isNaN(startMinute) ||
		      isNaN(endHour) ||
		      isNaN(endMinute)
		    ) {
		      return false;
		    }
		
		    return true;
		  },
		createFacility: function() {
			event.preventDefault();
			if (!this.validateWorkingHours()) {
		      alert("Invalid working hours format. Please use the format xx:xx-xx:xx with integer values for hours and minutes.");
		      return;
		    }
		    if (this.carFacility.name === '') {
			  alert("Name can't be empty.");
			  return;
			}
			if (this.carFacility.location === '') {
			  alert("Location can't be empty.");
			  return;
			}
			if (this.carFacility.logoPath === '') {
			  alert("You have to choose a logo");
			  return;
			}
			if (this.carFacility.workingHours === '') {
			  alert("You have to enter working hours");
			  return;
			}
			if (this.carFacility.manager === '') {
			  alert("You have to select a manager");
			  return;
			}
			console.log(this.carFacility);
			axios.post('/rest/facilities/new/', this.carFacility, {
				headers: { Authorization: `Bearer ${this.jwtt}` }
			})
			.then(response => {
				router.push(`/`);
			})
		},
		setManager: function(m) {
			this.carFacility.manager = m;
		},
		isSelected(index) {
			return index === this.selectedIndex;
		},
		openNewPage: function() {
		  	event.preventDefault();
			if (!this.validateWorkingHours()) {
		      alert("Invalid working hours format. Please use the format xx:xx-xx:xx with integer values for hours and minutes.");
		      return;
		    }
		    if (this.carFacility.name === '') {
			  alert("Name can't be empty.");
			  return;
			}
			if (this.carFacility.location === '') {
			  alert("Location can't be empty.");
			  return;
			}
			if (this.carFacility.logoPath === '') {
			  alert("You have to choose a logo");
			  return;
			}
			if (this.carFacility.workingHours === '') {
			  alert("You have to enter working hours");
			  return;
			}		  
			
			
			console.log(this.carFacility);
			axios.post('/rest/facilities/new/', this.carFacility, {
				headers: { Authorization: `Bearer ${this.jwtt}` }
			})
			.then(response => {
				router.push(`/register`);
			})
			

		}
	},
	mounted () {
		var toParse = localStorage.getItem('jwt');
		if(!toParse)
			alert("ERROR USER NOT LOGGED IN");
		else {
			this.jwtt = JSON.parse(toParse).jwt;
		}

		axios.get("/rest/users/managers/", {
			headers: { Authorization: `Bearer ${this.jwtt}` }
		}) 
		.then(response => {
			this.managers = response.data;
			for(const e of this.managers) {
				console.log(e.username);
				

				
				
			}
			

			
			
			
			
			
			// let temp = [...this.managers];

			// for(i = temp.length - 1; i >= 0; --i) {
			// 	if(temp[i].role !== 'MANAGER') {
			// 		let index = temp.indexOf(temp[i]);
			// 		this.managers.splice(index, 1);
			// 	}
			// }
		});
		
/*this.map = new ol.Map({
        target: 'map',
        layers: [
          new ol.layer.Tile({
            source: new ol.source.OSM()
          })
        ],
        view: new ol.View({
          center: ol.proj.fromLonLat([19, 45]),
          zoom: 4
        })
      }); 
  console.log(this.map);
  console.log("MAPO NAPAIRAJ SE "); */
			
    }
});