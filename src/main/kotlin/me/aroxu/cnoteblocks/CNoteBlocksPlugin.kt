package me.aroxu.cnoteblocks

import io.github.monun.kommand.kommand
import me.aroxu.cnoteblocks.CNoteBlocksCommand.register
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.type.NoteBlock
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.collections.HashMap

/**
 * @author aroxu
 */

class CNoteBlocksPlugin : JavaPlugin() {
    companion object {
        var enabled: HashMap<UUID, Boolean> = HashMap()
            private set
    }

    private val plugin: JavaPlugin = this
    private val creativeReach: Int = 5
    private val nonCreativeReach: Int = 4
    override fun onEnable() {
        kommand {
            register("cnb") {
                register(this)
            }
        }
        server.scheduler.scheduleSyncRepeatingTask(plugin, {
            val noteBlock: Material = Material.NOTE_BLOCK
            server.onlinePlayers.forEach { player ->
                if (enabled[player.uniqueId] == true) {
                    val reach: Int = if (player.gameMode == GameMode.CREATIVE) creativeReach else nonCreativeReach
                    val target: Block = player.getTargetBlock(reach) ?: return@forEach

                    val block: Material = target.type
                    if (block == noteBlock) {
                        val nb = target.blockData as NoteBlock
                        val note: String = nb.note.toString().removePrefix("Note{").removeSuffix("}")
                        val octave: Int = nb.note.octave + 1
                        var instrument: String = nb.instrument.toString()
                        when (instrument) {
                            "BASS_GUITAR" -> {
                                instrument = "베이스 기타"
                            }
                            "SNARE_DRUM" -> {
                                instrument = "스네어 드럼"
                            }
                            "STICKS" -> {
                                instrument = "막대기"
                            }
                            "BASS_DRUM" -> {
                                instrument = "베이스 드럼"
                            }
                            "BELL" -> {
                                instrument = "벨"
                            }
                            "FLUTE" -> {
                                instrument = "플루트"
                            }
                            "CHIME" -> {
                                instrument = "차임벨"
                            }
                            "GUITAR" -> {
                                instrument = "기타"
                            }
                            "XYLOPHONE" -> {
                                instrument = "실로폰"
                            }
                            "IRON_XYLOPHONE" -> {
                                instrument = "철제 실로폰"
                            }
                            "COW_BELL" -> {
                                instrument = "카우벨"
                            }
                            "DIDGERIDOO" -> {
                                instrument = "디저리두"
                            }
                            "BIT" -> {
                                instrument = "비트"
                            }
                            "BANJO" -> {
                                instrument = "밴조"
                            }
                            "PLING" -> {
                                instrument = "플링"
                            }
                            "PIANO" -> {
                                instrument = "피아노"
                            }
                        }
                        player.sendActionBar(
                            Component.text("악기: $instrument | 음: $note ($octave 옥타브)").decorate(TextDecoration.BOLD)
                                .color(TextColor.color(0xFFFFFF))
                        )
                    }
                } else {
                    return@scheduleSyncRepeatingTask
                }
            }

        }, 0, 1)
    }
}
