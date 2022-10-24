import { useEffect, useState } from "react";
import Restaurant from "./Restaurant";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faPlus } from "@fortawesome/free-solid-svg-icons";

const Restaurants = () => {

  const [rests,setRests] = useState([])
  const [update,setUpdate] = useState(false)  
  const [add, setAdd] = useState(false)
  library.add(faPlus);
  
  useEffect(() =>{  
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

  const restaurantUpdate = () => {
    setUpdate(!update)
  }

  const showAdd = () =>{
    setAdd(!add)
  }

  const addRestaurant = () => {
    const bod = {
      "address":document.getElementById("nrAddress").value,
      "name":document.getElementById("nrName").value
    };
    fetch(`api/restaurant`, {
      headers:{
        "Content-Type": "application/json",
      },
      method: "post",
      body : JSON.stringify(bod)
    })
    .then((response) => response.json())
    .then((data) => console.log(data));
    restaurantUpdate()
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
              <th className="restaurant-field">Address</th>
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
                <input id="nrName" type="text" />
              </td>
              <td className="restaurant-field">
                <input id="nrAddress" type="text" />
              </td>
              <td>
                <button onClick={addRestaurant}>save</button>
              </td>
              </tr>}
            {rests.map((rest, index) => (
              <Restaurant key = {index} restaurant = {rest} update = {restaurantUpdate}/>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}
  
export default Restaurants