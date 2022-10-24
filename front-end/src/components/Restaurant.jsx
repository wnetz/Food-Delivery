import { useEffect, useState } from "react"

const Restaurant = ({restaurant, update}) => {
    const [id, setId] = useState("")
    const [name, setName] = useState("")
    const [address, setAddress] = useState("")
    const [edit, setEdit] = useState(false)

    useEffect(()=>{
        setId(restaurant["restaurantID"])
        setName(restaurant["name"])
        setAddress(restaurant["address"])
        console.log('restaurant')
    },[restaurant])

    const showEdit = () =>{
      setEdit(!edit)
    }

    const updateRestaurant = () => {
      const bod = {
        "address":document.getElementById("rAddress").value,
        "name":document.getElementById("rName").value
      };
      fetch(`api/restaurant/${id}`, {
        headers:{
          "Content-Type": "application/json",
        },
        method: "put",
        body : JSON.stringify(bod)
      })
      update()
      showEdit()
    }

    const deleteRestaurant = () => {
      fetch(`api/restaurant/${id}`, {
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
    )
  }
  
  export default Restaurant