# Getting Started

1. Install Java 17
2. Run `docker-compose up` to start Rabbit container
3. Build: `./gradlew build`
4. Run: `./gradlew bootRun`

The TracingRabbitListenerAdvice which sets the trace id is invoked after all MessagePostProcessors ar completed so no
trace id available when logging in the message post processor.

Log output:

```
INFO [,a5fc7fdc23ee6ffb,a5fc7fdc23ee6ffb] 77043 --- [           main] c.t.r.producer.DemoProducer              : Sending payload...
INFO [,a5fc7fdc23ee6ffb,a5fc7fdc23ee6ffb] 77043 --- [           main] c.t.r.LoggingMessagePostProcessor        : MessagePostProcessor - Producer
INFO [,,] 77043 --- [ntContainer#0-1] c.t.r.LoggingMessagePostProcessor        : MessagePostProcessor - Consumer
INFO [,a5fc7fdc23ee6ffb,813fd257b05b276a] 77043 --- [ntContainer#0-1] c.t.r.consumer.DemoConsumer              : Event consumed by RabbitListener
```

The MessagePostProcessor used by the RabbitTemplate logs the trace id a5fc7fdc23ee6ffb according to:  
`INFO [,a5fc7fdc23ee6ffb,a5fc7fdc23ee6ffb] 77043 --- [           main] c.t.r.LoggingMessagePostProcessor        : MessagePostProcessor - Producer`

But the MessagePostProcessor used by the SimpleRabbitListenerContainerFactory is not logging any trace ids:  
`INFO [,,] 77043 --- [ntContainer#0-1] c.t.r.LoggingMessagePostProcessor        : MessagePostProcessor - Consumer`

Once the `@RabbitListener` method is invoked logs contains the trace id a5fc7fdc23ee6ffb:  
`INFO [,a5fc7fdc23ee6ffb,813fd257b05b276a] 77043 --- [ntContainer#0-1] c.t.r.consumer.DemoConsumer              : Event consumed by RabbitListener
`

