<script>
import axios from 'axios'
let imgJson = {}
let hasJsData = false
let httpUrl = 'http://175.24.165.47:6480/'
// let httpUrl = 'http://localhost:6480/'
function getImgJson () {
  if (hasJsData) {
    return imgJson
  } else {
    return new Promise((resolve, reject) => {
      axios.get(httpUrl + 'file/getDictionary').then(
        (res) => {
          imgJson = res.data.data
          console.log(imgJson)
          hasJsData = true
          resolve(imgJson)
        },
        (err) => {
          reject(err.data.data)
        }
      )
    })
  }
}
function hasData () {
  return hasJsData
}
function resetHasJsData () {
  hasJsData = false
}
export default {
  getImgJson,
  hasData,
  resetHasJsData
}
</script>
