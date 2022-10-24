import { useEffect, useState } from "react";
import Dish from "./Dish";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faPlus } from "@fortawesome/free-solid-svg-icons";

const Dishes = () => {
  
  const [rests,setRests] = useState([])
  const [dishes,setDishes] = useState([])
  const [update,setUpdate] = useState(false)  
  const [add, setAdd] = useState(false)
  library.add(faPlus);
  
  useEffect(() =>{  
    fetch("api/dishes", {
      headers:{
        "Content-Type": "application/json",
      },
      method: "get",
      //body : JSON.stringify(bod)
    })
    .then((response) => response.json())
    .then((data) => setDishes(data));
    fetch("api/restaurant", {
      headers:{
        "Content-Type": "application/json",
      },
      method: "get",
      //body : JSON.stringify(bod)
    })
    .then((response) => response.json())
    .then((data) => setRests(data));
  },[update]);
  const dishUpdate = () => {
    setUpdate(!update)
  }

  const showAdd = () =>{
    setAdd(!add)
  }

  const addDish = (restaurantID) => {
    const bod = {
      "price":document.getElementById("ndPrice").value,
      "name":document.getElementById("ndName").value
    };
    console.log(document.getElementById("nrestaurantID").value)
    fetch(`api/restaurant/${document.getElementById("nrestaurantID").value}/dishes`, {
      headers:{
        "Content-Type": "application/json",
      },
      method: "post",
      body : JSON.stringify(bod)
    })
    .then((response) => response.json())
    .then((data) => console.log(data));
    dishUpdate()
    showAdd()
  }
  return (
    <div className='container'>
      <div className='ph'>
        <table className="restaurants">
          <thead>
            <tr>
              <th className="restaurant-field">ID</th>
              <th className="restaurant-field">Name</th>
              <th className="restaurant-field">price</th>
              <th className="restaurant-field">restaurantID</th>
              <th>
                <button className = "add-btn" onClick={showAdd}>
                  <FontAwesomeIcon icon={"plus"} />
                </button>
              </th>
            </tr>
          </thead>
          <tbody>
            {add && <tr>
              <td>

              </td>
              <td className="restaurant-field">
                <input id="ndName" type="text" />
              </td>
              <td className="restaurant-field">
                <input id="ndPrice" type="text" />
              </td>
              <td className="restaurant-field">
                <select id="nrestaurantID">
                  {rests.map((rest,index) =>(
                    <option key = {index} value={rest["restaurantID"]}>{rest["name"]}</option>
                  ))}
                </select>
              </td>
              <td>
                <button onClick={addDish}>save</button>
              </td>
              </tr>}
            {dishes.map((dish, index) => (
              <Dish key = {index} dish = {dish} update = {dishUpdate}/>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}
  
export default Dishes