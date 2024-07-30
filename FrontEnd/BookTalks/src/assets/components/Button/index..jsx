import styles from "./styles.module.css";
import React, { useState, useEffect } from 'react';

export const Button = ({Titulo}) => {
return(
    <button className={styles.botao} onClick={console.log("valesca ppopozuda")}>
        {Titulo}
    </button>
)}
export default Button;