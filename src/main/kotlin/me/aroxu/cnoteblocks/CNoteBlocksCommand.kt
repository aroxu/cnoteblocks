package me.aroxu.cnoteblocks

import io.github.monun.kommand.node.LiteralNode
import net.kyori.adventure.text.Component

/**
 * @author aroxu
 */

object CNoteBlocksCommand {
    fun register(builder: LiteralNode) {
        builder.apply {
            then("about") { executes { sender.sendMessage("CNoteBlocks by aroxu.") } }
            then("disable") {
                executes {
                    CNoteBlocksPlugin.enabled[player.uniqueId] = false
                    sender.sendActionBar(Component.text(""))
                    sender.sendMessage("CNoteBlocks가 비활성화 되었습니다.")
                }
            }
            then("enable") {
                executes {
                    CNoteBlocksPlugin.enabled[player.uniqueId] = true
                    sender.sendMessage("CNoteBlocks가 활성화 되었습니다.")
                }
            }
        }
    }
}
