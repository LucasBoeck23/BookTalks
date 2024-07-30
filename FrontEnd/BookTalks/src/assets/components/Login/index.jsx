import { frases } from "../../Frases";
import Button from "../Button/index.";
import Input from "../Input";
import styles from "./styles.module.css";
import React, { useState, useEffect } from "react";

function Login() {
  const [frase, setFrase] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");

  useEffect(() => {
    gerarFrases();
  }, []);

  const gerarFrases = () => {
    const randomIndex = Math.floor(Math.random() * frases.length);
    setFrase(frases[randomIndex]);
  };
  const handleEmail = (event) => {
    setEmail(event.target.value);
  };
  const handleSenha = (event) => {
    setSenha(event.target.value);
  };

  return (
    <>
      <div className={styles.contentContainer}>
        <div className={styles.containerLogo}>
          <h1>BookTalks</h1>
          <h2>{frase}</h2>
        </div>
        <div className={styles.containerLogin}>
          <div className={styles.contentLogin}>
            <div className={styles.Input}>
            <p>Email:</p>
            <Input
              type="email"
              placeholder="E-mail"
              value={email}
              onChange={handleEmail}
              />
            <p>Senha:</p>
            <Input
              type="password"
              placeholder="Senha"
              value={senha}
              onChange={handleSenha}
              />
              </div>
            <div className={styles.Button}>
              <Button Titulo={"Login"}/>
            </div>
            <div className={styles.cadastro}>
                <p>Não possui uma conta?</p>
                <a onClick={console.log("cadastrou")}>Cadastre-se</a>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Login;
