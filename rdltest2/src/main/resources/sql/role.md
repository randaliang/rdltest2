sample
===
* 注释

	select #use("cols")# from user  where  #use("condition")#

cols
===
	id,name,age,userName,roleId,create_date

updateSample
===
	
	id=#id#,name=#name#,age=#age#,userName=#userName#,roleId=#roleId#,create_date=#create_date#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(age)){
	 and age=#age#
	@}
	@if(!isEmpty(userName)){
	 and userName=#userName#
	@}
	@if(!isEmpty(roleId)){
	 and roleId=#roleId#
	@}
	@if(!isEmpty(create_date)){
	 and create_date=#create_date#
	@}
	
	
querybyname
===
select #use("cols")# from user where name like #name#


queryNewUser
===
select * from user where name like #name# order by id desc 

queryNewUser$count
===
select count(1) from user where name like #name# 



query1SqlNewUser
===
select 
@pageTag(){
	id,name
@}
 from user where name like #name# 
 
 
query2SqlNewUser
===
select page("*") from user where name like #name# 
  @pageIgnoreTag(){
   order by id 
  @}