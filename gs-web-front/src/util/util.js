// 设置cookie
export function setCookie (cName, value, expire) {
  let date = new Date()
  date.setSeconds(date.getSeconds() + expire)
  document.cookie = cName + '=' + escape(value) + '; expires=' + date.toUTCString()
  // console.log(document.cookie)
}
// 获取cookie
export function getCookie (cName) {
  if (document.cookie.length > 0) {
    let CStart = document.cookie.indexOf(cName + '=')
    if (CStart !== -1) {
      CStart = CStart + cName.length + 1
      let cEnd = document.cookie.indexOf(';', CStart)
      if (cEnd === -1) {
        cEnd = document.cookie.length
      }
      return unescape(document.cookie.substring(CStart, cEnd))
    }
  }
  return ''
}
// 删除cookie
export function delCookie (cName) {
  setCookie(cName, '', -1)
}
