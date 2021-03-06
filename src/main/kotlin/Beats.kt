import java.io.File
import javax.sound.sampled.AudioSystem
import kotlinx.coroutines.*

suspend fun playBeat(beats: String, file: String){
    val parts = beats.split("x")
    var count = 0
    for (part in parts){
        count += part.length + 1
        if (part == ""){
            playSound(file)
        }else{
            delay(100 * (part.length + 1L))
            if (count < beats.length){
                playSound(file)
            }
        }
    }
}

fun playSound(file: String){
    val clip = AudioSystem.getClip()
    val audioInputStream = AudioSystem.getAudioInputStream(File(file))
    clip.open(audioInputStream)
    clip.start()
}

suspend fun main(){
    // Cria chamadas assíncronas na mesma Thread, porém em coroutine diferentes
    runBlocking {
        launch {playBeat("x-x-x-x-x-x", "toms.aiff")}
        playBeat("x-----x-----", "crash_cymbal.aiff")
    }
}