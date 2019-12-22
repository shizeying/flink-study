package com.scala.study.wordcount

import org.apache.flink.streaming.api.scala._

/**
  * @author shizeying
  * @date 2019/12/22 16:22
  * @description
  */
object WordCountScala {

  val WORDS = Array(
    "To be, or not to be,--that is the question:--",
    "Whether 'tis nobler in the mind to suffer"
  )

  def main(args: Array[String]): Unit = {
    import org.apache.flink.api.java.utils.ParameterTool
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //配置全局参数
    env.getConfig.setGlobalJobParameters(ParameterTool.fromArgs(args))
    env
      .fromElements(WORDS)
      .flatMap(_.mkString.toLowerCase.split("\\W+"))
      .map((_, 1))
      .keyBy(0)
      .sum(1)
      .print()
    env.execute("wordCount")

  }
}
