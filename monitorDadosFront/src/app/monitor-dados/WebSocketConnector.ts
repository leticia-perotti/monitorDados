import { Stomp } from "@stomp/stompjs";
import * as SockJS from "sockjs-client";

export class WebSocketConnector{

  private stompClient: any

  constructor(
    private webSoccketEndPoint: string,
    private topic: string,
    private onMessage: Function,
    private callbackError?: Function){

    const errorCallback = callbackError || this.onError;
    this.connect(errorCallback)
  }

  connect(errorCallback: Function){
    console.error("Iniciando a conexão")

    const ws = new SockJS(this.webSoccketEndPoint)
    this.stompClient = Stomp.over(ws)
    this.stompClient.connect({} , (frame: any)=> {
      this.stompClient.subscribe(this.topic, (event: any) => {
        this.onMessage(event)
      });
      errorCallback(this)
    })
  }

  onError(error: any){
    console.log("Erro ao conectar")
    setTimeout(() => {
      console.log("Conectrando novamente...")
      this.connect(this.onError)
    }, 3000)
  }



}
