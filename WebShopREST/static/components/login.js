Vue.component("Login", {
	data: function () {
		    return {
			message: "test",
			role: "",
			login: {
				username: "",
				password: "",
			},
			
			basket: { username:"TEST99", price: 0.0, content:"0", } 
			
			
		  }
	},
	template: ` 
<div id="login-div">
	<form>
		<table>
			<tr>
				<td>
					<p>Username: </p>
					<input type="text" v-model = "login.username"></input>
				</td>
			</tr>
			<tr>
				<td>
					<p>Password: </p>
					<input type="password" v-model = "login.password"></input>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align:center">
					<input type="submit" value="Login" v-on:click = "loginConfirm" id="login-button" class="btn btn-info"></input>
				</td>
			</tr>
		</table>
	</form>
</div>
`
	, 
	methods : {
		loginConfirm : function() {
			event.preventDefault();
    		axios.get("/rest/users/login/", {params: { username: this.login.username, password: this.login.password }})
    		.then(response => {
				if(response.data != null) {
					//console.log(response.data);
					this.role = response.data.role;
					var parsed = JSON.stringify(response.data);
					localStorage.setItem('jwt', parsed);
					
					this.$root.$emit('messageFromChild1ToChild2', 'true');
					this.$root.$emit('usernameMessage', response.data.username);
				
					router.push(`/`);
				} else {
					alert("Username or password are invalid");
				}
			}).catch(error => {
    			console.log(error.response)
			});
			
			axios.post('rest/basket/new/', this.basket, { });
			console.log(this.basket);
			//router.push(`/`);
    	}
	},
	mounted () {
		/*this.id = this.$route.params.id;
		if (this.id != -1){
	        axios
	          .get('rest/products/' + this.id)
	          .then(response => (this.product = response.data))
		}*/
    }
});