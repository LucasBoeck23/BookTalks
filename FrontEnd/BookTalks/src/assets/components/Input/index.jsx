import styles from "./styles.module.css";
import React, { useState, useEffect } from 'react';

export const Input = ({type, placeholder ,value ,onChange }) => {
    return(
    <input className={styles.input}
    type={type}
    placeholder={placeholder}
    value={value}
    onChange={onChange}/>
)}
export default Input;