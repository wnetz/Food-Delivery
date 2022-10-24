import { useEffect, useState } from "react";
import Dish from "./Dish";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const Dishes = () => {
  
  const [dish,setDish] = useState([])

  const bod = {
      "address":"dghm",
      "name":"lob"
  };
  
  useEffect(() =>{  
    fetch("api/dishes", {
      headers:{
        "Content-Type": "application/json",
      },
      method: "get",
      //body : JSON.stringify(bod)
    })
    .then((response) => response.json())
    .then((data) => setDish(data));
  },[]);
  return (
    <div className='container'>
      <div className='top'>
        <button className = "add-btn">
          <FontAwesomeIcon icon={"plus"} />
        </button>
      </div>
      <div className='ph'>
        <table className="restaurants">
          <thead>
            <tr>
              <th className="restaurant-field">ID</th>
              <th className="restaurant-field">Name</th>
              <th className="restaurant-field">Address</th>
            </tr>
          </thead>
          <tbody>
            {dish.map((rest, index) => (
              <Dish key = {index} dish = {rest}/>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}
  
export default Dishes