def hex = "4E:F4:BD:17:6A:17:0D:20:92:1E:1E:C9:A7:C6:A5:B8:16:EA:1C:07:57:EA:C8:06:01:EA:14:8C:04:59:E0:AA".replaceAll(":","")
def bin = hex.decodeHex()
def b64 = bin.encodeBase64().toString()

println hex
println b64

hex = "31:BE:ED:BF:FB:E8:44:D4:C2:F8:B3:16:1C:83:04:60:BE:0D:B8:46:B1:D0:7D:DD:EE:05:10:AF:6F:71:F8:F1".replaceAll(":","")

bin = hex.decodeHex()
b64 = bin.encodeBase64().toString()

println hex
println b64


return 0