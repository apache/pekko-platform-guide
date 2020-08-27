package shopping.order

import akka.actor.typed.ActorSystem
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.AbstractBehavior
import akka.actor.typed.scaladsl.ActorContext
import akka.actor.typed.scaladsl.Behaviors
import akka.management.cluster.bootstrap.ClusterBootstrap
import akka.management.scaladsl.AkkaManagement

object Main {

  def main(args: Array[String]): Unit = {
    ActorSystem[Nothing](Main(), "ShoppingOrder")
  }

  def apply(): Behavior[Nothing] = {
    Behaviors.setup[Nothing](context => new Main(context))
  }
}

class Main(context: ActorContext[Nothing]) extends AbstractBehavior[Nothing](context) {
  val system = context.system

  AkkaManagement(system).start()
  ClusterBootstrap(system).start()

  val grpcInterface =
    context.system.settings.config.getString("shopping-order.grpc.interface")
  val grpcPort =
    context.system.settings.config.getInt("shopping-order.grpc.port")
  ShoppingOrderServer.start(grpcInterface, grpcPort, system)

  override def onMessage(msg: Nothing): Behavior[Nothing] =
    this

}
