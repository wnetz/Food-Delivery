import { useEffect, useState } from "react"

const Dish = ({dish}) => {
    const [id, setId] = useState("")
    const [name, setName] = useState("")
    const [price, setAddress] = useState(0.0)
    console.log(dish)
    useEffect(()=>{
        setId(dish["dishID"])
        setName(dish["name"])
        setAddress(dish["price"])
    })

    return (
      <tr className="restaurant-fields">
        <td className="restaurant-field">{id}</td>
        <td className="restaurant-field">{name}</td>
        <td className="restaurant-field">{price}</td>
      </tr>
    )
  }
  
  export default Dish