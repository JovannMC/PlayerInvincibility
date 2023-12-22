# MentionChat

Let players be invincible to other player attacks<br>

## Description

A simple and open source plugin, originally made for a private server, that lets players be invincible to other players attacking them. I couldn't find a plugin similar to this one online, so I made it myself.

<details>
    <summary>config.yml</summary>

    # PlayerInvinciblity config.yml
    # List of players who are invincible from other players' attacks
    invincible: ["player1", "player2", "player3"]
    
    # Logs when a player attempts to damage an invincible player in the console
    logInvincibleDamage: true
    # Sends a message to the damaged and damager when they attempt to damage an invincible player
    sendInvincibleMessage: true
    # Broadcasts a message to the server when a player attempts to damage an invincible player
    broadcastInvincibleMessage: false
    
    # Messages
    invincibleMessage: "You were attempted to be damaged by %damager% but you are invincible!"
    invincibleMessageDamager: "You attempted to damage %damaged% but they are invincible!"
    invincibleMessageBroadcast: "%damager% attempted to damage %damaged% but they are invincible!"
    
    # DO NOT TOUCH THIS
    configVersion: 1
</details>

## Commands

- `/playerinvincibility` - PlayerInvincibility command
- `/playerinvincibility reload` - Reload PlayerInvincibility
- `/playerinvincibility add <player>` - Add a player to the invincibility list
- `/playerinvincibility remove <player>` - Remove a player from the invincibility list

## Permissions

- `playerinvincibility.*` - Gives access to all PlayerInvincibility permission nodes. Default is `OP`.
- `playerinvincibility.info` - Use /playerinvincibility command. Default is `everyone`.
- `playerinvincibility.reload` - Reload PlayerInvincibility. Default is `OP`.
- `playerinvincibility.bypass` - Bypass a player's invincibility. Default is `OP`.
- `playerinvincibility.add` - Add a player to the invincibility list. Default is `OP`.
- `playerinvincibility.remove` - Remove a player from the invincibility list. Default is `OP`.

## License

This project is licensed under the [MIT](https://opensource.org/license/mit/) License - see the [LICENSE.md](LICENSE.md) file for details.
