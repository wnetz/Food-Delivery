import { useEffect, useState } from "react"

const Dish = ({dish,update}) => {
  const [id, setId] = useState("")
  const [name, setName] = useState("")
  const [price, setPrice] = useState(0.0)
  const [restaurant, setRestaurant] = useState(0)
  const [edit, setEdit] = useState(false)    
  const [rests,setRests] = useState([])

  useEffect(()=>{
    setId(dish["id"])
    setName(dish["name"])
    setPrice(dish["price"])
    setRestaurant(dish["restaurantID"])    
    fetch("api/restaurant", {
      headers:{
        "Content-Type": "application/json",
      },
      method: "get",
      //body : JSON.stringify(bod)
    })
    .then((response) => response.json())
    .then((data) => setRests(data));
  },[dish])

  const showEdit = () =>{
    setEdit(!edit)
  }

  const updateDish = () => {
    const bod = {
      "price":document.getElementById("dPrice").value,
      "name":document.getElementById("dName").value
    };
    var method = "put"
    if(document.getElementById("restaurantID").value !== restaurant)
    {
      deleteDish()
      method = "post"
    }
    fetch(`api/restaurant/${document.getElementById("restaurantID").value}/dishes`, {
      headers:{
        "Content-Type": "application/json",
      },
      method: method,
      body : JSON.stringify(bod)
    })
    update()
    showEdit()
  }

  const deleteDish = () => {
    fetch(`api/dishes/${id}`, {
      headers:{
        "Content-Type": "application/json",
      },
      method: "delete",
    })
    update()
    showEdit()
  }

  return (
    <tr className="restaurant-fields">
      <td className="restaurant-field">{id}</td>
      {!edit && <>
        <td className="restaurant-field">{name}</td>
        <td className="restaurant-field">{price}</td>        
        <td className="restaurant-field">{restaurant}</td>
        <td>
          <button onClick={showEdit}>edit</button>
        </td>
      </>}
      {edit && <>
        <td className="restaurant-field">
          <input id="dName" type="text" defaultValue={name}/>
        </td>
        <td className="restaurant-field">
          <input id="dPrice" type="text" defaultValue={price}/>
        </td>
        <td className="restaurant-field">
          <select id="restaurantID" defaultValue={restaurant}>
            {rests.map((rest,index) =>(
              <option key = {rest["restaurantID"]} value={rest["restaurantID"]}>{rest["name"]}</option>
            ))}
          </select>
        </td>
        <td>
          <button onClick={updateDish}>save</button>
          <button onClick={deleteDish}>delete</button>
        </td>
      </>} 
    </tr>
  )
}
  
  export default Dish

  

  /*return (
    <tr className="restaurant-fields">
      <td className="restaurant-field">{id}</td>
      {!edit && <>
        <td className="restaurant-field">{name}</td>
        <td className="restaurant-field">{address}</td>
        <td>
          <button onClick={showEdit}>edit</button>
        </td>
      </>}
      {edit && <>
        <td className="restaurant-field">
          <input id="rName" type="text" defaultValue={name}/>
        </td>
        <td className="restaurant-field">
          <input id="rAddress" type="text" defaultValue={address}/>
        </td>
        <td>
          <button onClick={updateRestaurant}>save</button>
          <button onClick={deleteRestaurant}>delete</button>
        </td>
      </>}      
    </tr>
  )*/