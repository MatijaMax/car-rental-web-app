Vue.component("Register", {
	data: function () {
		return {
			message: "test",
			isAdmin: true,
			user: {
				username: "",
				password: "",
				name: "",
				surname: "",
				gender: 'MALE',
				dateofBirth: "",
				role: 'CUSTOMER'
			},
			confirmPassword: '',
			passwordMessage: ''
		}
	},
	template: ` 
<div id="register-div">
	<div class="inner-div">
		<form>
			<table>
				<tr>
					<td>
						<p>Username: </p>
						<input type="text" v-model = "user.username"></input>
					</td>
				</tr>
				<tr>
					<td>
						<p>Password: </p>
						<input type="password" v-model = "user.password"></input>
					</td>
				</tr>
        <tr>
          <td>
            <p>Confirm Password: {{passwordMessage}} </p>
            <input type="password" v-model = "confirmPassword"  @change="confPassword"></input>
          </td>
			  </tr>
				<tr>
					<td>
						<p>Name: </p>
						<input type="text" v-model = "user.name"></input>
					</td>
				</tr>
				<tr>
					<td>
						<p>Surname: </p>
						<input type="text" v-model = "user.surname"></input>
					</td>
				</tr>
				<tr>
					<td>
						<input type="radio" value="MALE" v-model="user.gender" :checked="user.gender === 'MALE'">Male</input>
						<input type="radio" value="FEMALE" v-model="user.gender">Female</input>
					</td>
				</tr>
				<tr>
					<td>
						<p>Date Of Birth: </p>
						<input type="date" v-model="user.dateofBirth"></input>
					</td>
				</tr>
				<tr v-if = "this.user.role === 'ADMIN'">
					<td colspan="2" style="text-align:center">
							<input type="hidden" v-model="user.role" value="MANAGER" />
							<p>Role: Manager</p>
						</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value="Register" v-on:click = "registerUser" class="btn btn-info"></input>
					</td>
				</tr>
				
			</table>
		</form>

		<div v-if = "this.user.role === 'CUSTOMER'">
			<p>Already have an account?</p>
			<button  v-on:click = "showLogin" class="btn btn-info"> Login here. </button>
		</div>
	</div>
</div>
`
	, 
	methods : {
		registerUser : function() {
			if(this.user.role=='ADMIN'){
				this.user.role='MANAGER';
			}
			if(this.user.password != this.confirmPassword) {
				alert("Passwords don't match");
				return;
			}
			event.preventDefault();
			if (this.user.username === '') {
			  alert("Username can't be empty.");
			  return;
			}
			if (this.user.password === '') {
			  alert("Username can't be empty.");
			  return;
			}
			if (this.user.name === '') {
			  alert("Name can't be empty.");
			  return;
			}
			if (this.user.surname === '') {
			  alert("Surname can't be empty.");
			  return;
			}
			if (this.user.gender === '') {
			  alert("Gender can't be empty.");
			  return;
			}
			if (this.user.dateofBirth === '') {
			  alert("Date of birth can't be empty.");
			  return;
			}
			if (this.user.role === '') {
			  alert("You have to select a role.");
			  return;
			}
			axios
				.post('rest/users/register/', this.user)
				.then(response => {
					
				    router.push(`/`);
				  
				})
		},
		showLogin: function () {
			router.push(`/login`);
		},
		confPassword: function() {
			if(this.user.password === this.confirmPassword) {
				this.passwordMessage = '';
			} else {
				this.passwordMessage = 'PLEASE ENTER THE SAME PASSWORD!!';				
			}
			
		}
		
	},
	mounted() {
		this.user.role = this.$route.params.role;

		var toParse = localStorage.getItem('jwt');
		var role;

		if (!toParse)
			role = 'CUSTOMER'
		else
			role = JSON.parse(toParse).role;

		this.user.role = role;
		if (this.$route.query.manager) {
    		this.user.manager = this.$route.query.manager;
  }
	}
});