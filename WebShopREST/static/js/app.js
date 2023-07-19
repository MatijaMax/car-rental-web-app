const Register = { template: '<register></register>' }
const Login = {template: '<login></login>'}
const Mainpage = {template: '<mainpage></mainpage>' }
const Navbar = {template: '<navbar></navbar>' }
const MyProfile = { template: '<my-profile></my-profile>' }
const Allusers = {template: '<allusers></allusers>'}
const Createfacility = {template: '<createfacility></createfacility>' }
const Facilitypage = {template: '<facilitypage></facilitypage>'}
const Comments = {template: '<comments></comments>'}

const Allrents ={template: '<allrents></allrents>'}
const Allvehicles ={template: '<allvehicles></allvehicles>'}
const Basket ={template: '<basket></basket>'}

const router = new VueRouter({
	mode : 'hash',
	  routes: [
		{ path: '/login', component: Login},
		{ path: '/createFacility', component: Createfacility},
		{ path: '/register',name: 'home', component: Register},
		{ path: '/', component: Mainpage},
		{ path: '/profile', component: MyProfile},
		{ path: '/facilityPage/:facilityID', name: 'facilityPage', component: Facilitypage},
		{ path: '/allUsers', component: Allusers},
		
		{ path: '/allRents', component: Allrents},
		{ path: '/allVehicles', component: Allvehicles},
		{ path: '/basket', component: Basket}
		
	  ]
});

var app = new Vue({
	router,
	el: '#START'
});