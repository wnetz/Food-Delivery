import React from "react"
import { Link, } from "react-router-dom"
import './../styles/nav.css'

/**
 * @param {{ uid: any; setUid: any; }} props
 */
export default function Navbar(props) {

  return (
    <div className="header">
        <nav >
            <Link className="navbar_link" to = "/">Home</Link>
            <Link className="navbar_link" to = "/Restaurants">Restaurants</Link>
            <Link className="navbar_link" to = "/Dishes">Dishes</Link>
        </nav>
    </div>

  )
}